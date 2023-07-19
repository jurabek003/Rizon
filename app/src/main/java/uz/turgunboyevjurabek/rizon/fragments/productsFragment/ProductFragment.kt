package uz.turgunboyevjurabek.rizon.fragments.productsFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import uz.turgunboyevjurabek.rizon.adapters.MyProductsAdapter
import uz.turgunboyevjurabek.rizon.databinding.FragmentProductBinding
import uz.turgunboyevjurabek.rizon.utils.AppObject
import uz.turgunboyevjurabek.rizon.utils.Status

private const val TAG = "ProductFragment"

class ProductFragment : Fragment() {
    private val binding by lazy { FragmentProductBinding.inflate(layoutInflater) }
    private lateinit var productsViewModel: ProductsViewModel
    private lateinit var myProductsAdapter: MyProductsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        productsViewModel = ViewModelProvider(requireActivity())[ProductsViewModel::class.java]

        myProductsAdapter = MyProductsAdapter()
        binding.rvUsersProducts.adapter = myProductsAdapter

        productsViewModel.getUsersProducts("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg5ODIzNTQ1LCJpYXQiOjE2ODkzOTE1NDUsImp0aSI6IjNmNDBhZTIxOTRiMjQ2YjFiOTdiODA4NDhmMjliODllIiwidXNlcl9pZCI6MTU4fQ.FvJu6ND6sHW2pBNXb8cEn_DKY4ruXwqMCSkGt6C7k6Q")
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
    }
}