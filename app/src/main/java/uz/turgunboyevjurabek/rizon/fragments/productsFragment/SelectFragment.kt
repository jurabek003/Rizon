package uz.turgunboyevjurabek.rizon.fragments.productsFragment

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.squareup.picasso.Picasso
import uz.ilhomjon.rizonuz.databinding.FragmentSelectBinding
import uz.ilhomjon.rizonuz.databinding.ItemAddOrderDialogBinding
import uz.turgunboyevjurabek.rizon.madels.usersProducts.Product
import uz.turgunboyevjurabek.rizon.madels.usersProducts.post.PostProductsOrder
import uz.turgunboyevjurabek.rizon.retrofit.ApiClient
import uz.turgunboyevjurabek.rizon.utils.MySharedPreference
import uz.turgunboyevjurabek.rizon.utils.Status

private const val TAG = "SelectFragment"
class SelectFragment : Fragment() {
    private val binding by lazy { FragmentSelectBinding.inflate(layoutInflater) }
    lateinit var productsViewModel: ProductsViewModel
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        productsViewModel = ViewModelProvider(requireActivity()).get(ProductsViewModel::class.java)
        selectItem()
        binding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    private fun selectItem() {
        val product : Product =arguments?.getSerializable("keyProduct") as Product
        Picasso.get().load("${ApiClient.PHOTO_BASE_URL}${product.photo_link}").into(binding.selectItemImg)
        binding.selectItemName.text=product.name
        binding.selectItemPrice.text="${product.price}"
        binding.selectItemAbout.text=product.about

        binding.btnPlus.setOnClickListener {
            binding.tvCount.text = (binding.tvCount.text.toString().toInt() + 1).toString()
            binding.tvUmumiySumma.text = (binding.tvCount.text.toString().toInt() * product.price).toString()
        }
        binding.btnMinus.setOnClickListener {
            if (binding.tvCount.text.toString().toInt()>1) {
                binding.tvCount.text = (binding.tvCount.text.toString().toInt() - 1).toString()
                binding.tvUmumiySumma.text =
                    (binding.tvCount.text.toString().toInt() * product.price).toString()
            }
        }

        binding.btnOrder.setOnClickListener {
            addOrder(product, binding.tvCount.text.toString().toInt())
        }
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
                            nameList.add("${it.name} ${it.address}")
                        }
                        itemDialog.spinnerFilial.adapter = ArrayAdapter<String>(binding.root.context, android.R.layout.simple_list_item_1, nameList)

                        itemDialog.materialButton2.setOnClickListener {
                            applyOrder(
                                dialog,
                                itemDialog,
                                PostProductsOrder(
                                    itemDialog.tvCount.text.toString().toInt(),
                                    product.id,
                                    list?.get(itemDialog.spinnerFilial.selectedItemPosition)!!.id
                                )
                            )
                        }
                    }
                }
            }

        dialog.setContentView(itemDialog.root)
        dialog.show()
    }

    fun applyOrder(alertDialog: BottomSheetDialog, itemDialog: ItemAddOrderDialogBinding, postProductsOrder: PostProductsOrder){
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
                        dialog.setMessage("${it.data?.product} maxsuloti ${it.data?.warehouse} filialiga ${it.data?.amount} ta buyurtma qilindi. Buyurtmalar oynasidan ko'rishingiz mumkin.")
                        dialog.show()
                        itemDialog.myProgressBar.visibility = View.GONE
                        alertDialog.cancel()
                    }
                }
            }
    }

}