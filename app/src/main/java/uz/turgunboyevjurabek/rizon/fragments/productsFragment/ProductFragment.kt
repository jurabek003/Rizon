package uz.turgunboyevjurabek.rizon.fragments.productsFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.turgunboyevjurabek.rizon.R
import uz.turgunboyevjurabek.rizon.adapters.MyProductsAdapter
import uz.turgunboyevjurabek.rizon.adapters.SelectItem
import uz.turgunboyevjurabek.rizon.databinding.FragmentProductBinding
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
        findNavController().navigate(R.id.selectFragment, bundleOf("keyName" to product.name,
            "keyPrice" to product.price,"keyPhoto" to product.photo_link,"keyAbout" to product.about))
    }

    override fun buyurtmaBtn(
        position: Int,
        product: uz.turgunboyevjurabek.rizon.madels.usersProducts.Product,
        count:Int
    ) {

//        val postOrderProducts = PostOrderProducts(count, false, product, )

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