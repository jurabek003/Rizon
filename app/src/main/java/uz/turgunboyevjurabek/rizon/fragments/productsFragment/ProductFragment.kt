package uz.turgunboyevjurabek.rizon.fragments.productsFragment

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.squareup.picasso.Picasso
import uz.ilhomjon.rizonuz.R
import uz.ilhomjon.rizonuz.databinding.FragmentProductBinding
import uz.ilhomjon.rizonuz.databinding.ItemAddOrderDialogBinding
import uz.turgunboyevjurabek.rizon.adapters.MyProductsAdapter
import uz.turgunboyevjurabek.rizon.adapters.SelectItem
import uz.turgunboyevjurabek.rizon.madels.usersProducts.Product
import uz.turgunboyevjurabek.rizon.madels.usersProducts.post.PostProductsOrder
import uz.turgunboyevjurabek.rizon.retrofit.ApiClient
import uz.turgunboyevjurabek.rizon.utils.AppObject
import uz.turgunboyevjurabek.rizon.utils.MySharedPreference
import uz.turgunboyevjurabek.rizon.utils.Status

private const val TAG = "ProductFragment"

class ProductFragment : Fragment(),SelectItem {
    private val binding by lazy { FragmentProductBinding.inflate(layoutInflater) }
    private lateinit var productsViewModel: ProductsViewModel
    private lateinit var myProductsAdapter: MyProductsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        productsViewModel = ViewModelProvider(requireActivity())[ProductsViewModel::class.java]

        myProductsAdapter = MyProductsAdapter(ArrayList(),this)
        binding.rvUsersProducts.adapter = myProductsAdapter
        MySharedPreference.init(binding.root.context)

        productsViewModel.getUsersProducts(MySharedPreference.token)
            .observe(requireActivity()){
                when(it.status){
                    Status.LOADING ->{
                        Log.d(TAG, "onCreate: Loading")
                        binding.progressUserProducts.visibility = View.VISIBLE
                    }
                    Status.ERROR ->{
                        Log.d(TAG, "onCreate: Error ${it.message}")
                        binding.progressUserProducts.visibility = View.GONE
                        Toast.makeText(context, "Error ${it.message}", Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS ->{
                        Log.d(TAG, "onCreate: ${it.data}")
                        myProductsAdapter.list.clear()
                        myProductsAdapter.list.addAll(it.data?.products!!)
                        myProductsAdapter.notifyDataSetChanged()
                        binding.progressUserProducts.visibility = View.GONE
                    }
                }
            }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        AppObject.binding.thtPanel.text = "Mahsulotlar"
        AppObject.binding.materialCardViewCalendar.visibility = View.INVISIBLE
    }

    override fun onClick(position: Int, product: uz.turgunboyevjurabek.rizon.madels.usersProducts.Product) {
        findNavController().navigate(R.id.selectFragment, bundleOf("keyProduct" to product))
    }

    override fun buyurtmaBtn(
        position: Int,
        product: uz.turgunboyevjurabek.rizon.madels.usersProducts.Product,
        count:Int
    ) {
        addOrder(product, count)
    }

    fun addOrder(product: Product, count: Int){
        val dialog = BottomSheetDialog(binding.root.context)
        val itemDialog = ItemAddOrderDialogBinding.inflate(layoutInflater)

        itemDialog.itemPrice.text = product.price.toString()
        itemDialog.itemName.text = product.name
        itemDialog.tvUmumiySumma.text = "${product.price*count}"
        Picasso.get().load("${ApiClient.PHOTO_BASE_URL}${product.photo_link}").into(itemDialog.itemImg)
        itemDialog.tvCount.text = count.toString()

        productsViewModel.getAllFilial(MySharedPreference.token)
            .observe(viewLifecycleOwner){
                when(it.status){
                    Status.LOADING ->{
                        Log.d(TAG, "onCreate: Loading")
                        itemDialog.itemContainer.visibility = View.INVISIBLE
                        itemDialog.myProgressBar.visibility = View.VISIBLE
                    }
                    Status.ERROR ->{
                        Log.d(TAG, "onCreate: Error ${it.message}")
                        itemDialog.myProgressBar.visibility = View.GONE
                        Toast.makeText(context, "Error ${it.message}", Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS ->{
                        Log.d(TAG, "onCreate: ${it.data}")
                        itemDialog.itemContainer.visibility = View.VISIBLE
                        itemDialog.myProgressBar.visibility = View.GONE
                        val nameList = ArrayList<String>()
                        val list = it.data
                        it.data?.forEach {
                            nameList.add("${it.name} ")
                        }
                        itemDialog.spinnerFilial.adapter = ArrayAdapter<String>(binding.root.context, android.R.layout.simple_list_item_1, nameList)

                        itemDialog.materialButton2.setOnClickListener {
                            applyOrder(
                                product,
                                list?.get(itemDialog.spinnerFilial.selectedItemPosition)?.name!!,
                                dialog,
                                itemDialog,
                                PostProductsOrder(
                                itemDialog.tvCount.text.toString().toInt(),
                                product.id,
                                list?.get(itemDialog.spinnerFilial.selectedItemPosition)!!.id
                            ))
                        }
                    }
                }
            }

        dialog.setContentView(itemDialog.root)
        dialog.show()
    }

    fun applyOrder(product: Product, filial:String, alertDialog: BottomSheetDialog, itemDialog:ItemAddOrderDialogBinding, postProductsOrder: PostProductsOrder){
        productsViewModel.postProductsOrder(MySharedPreference.token, postProductsOrder)
            .observe(viewLifecycleOwner){
                when(it.status){
                    Status.LOADING ->{
                        Log.d(TAG, "onCreate: Loading")
                        itemDialog.itemContainer.isEnabled = false
                        itemDialog.myProgressBar.visibility = View.VISIBLE
                    }
                    Status.ERROR ->{
                        Log.d(TAG, "onCreate: Error ${it.message}")
                        itemDialog.myProgressBar.visibility = View.GONE
                        itemDialog.itemContainer.isEnabled = true
                        Toast.makeText(context, "Error ${it.message}", Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS ->{
                        Log.d(TAG, "onCreate: ${it.data}")
                        val dialog = AlertDialog.Builder(binding.root.context)
                        dialog.setMessage("${product.name} maxsuloti $filial filialiga ${it.data?.amount} ta buyurtma qilindi. Buyurtmalar oynasidan ko'rishingiz mumkin.")
                        dialog.show()
                        itemDialog.myProgressBar.visibility = View.GONE
                        alertDialog.cancel()
                    }
                }
            }
    }

    override fun plusCount(
        position: Int,
        product: uz.turgunboyevjurabek.rizon.madels.usersProducts.Product
    ) {

    }

    override fun minusCount(
        position: Int,
        product: uz.turgunboyevjurabek.rizon.madels.usersProducts.Product
    ) {

    }
}