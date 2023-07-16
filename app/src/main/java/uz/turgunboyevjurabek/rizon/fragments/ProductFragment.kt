package uz.turgunboyevjurabek.rizon.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import uz.turgunboyevjurabek.rizon.R
import uz.turgunboyevjurabek.rizon.adapters.MyProductsAdapter
import uz.turgunboyevjurabek.rizon.databinding.FragmentProductBinding
import uz.turgunboyevjurabek.rizon.utils.Status
import uz.turgunboyevjurabek.rizon.viewmodel.AppViewModel
import uz.turgunboyevjurabek.rizon.viewmodel.MyViewModelFactory
import uz.turgunboyevjurabek.rizon.viewmodel.MyViewModelObjects

private const val TAG = "ProductFragment"
class ProductFragment : Fragment() {
    private val binding by lazy { FragmentProductBinding.inflate(layoutInflater) }
    private lateinit var appViewModel: AppViewModel
    private lateinit var myProductsAdapter: MyProductsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        appViewModel = ViewModelProvider(this, MyViewModelFactory(MyViewModelObjects.appRepository)).get(AppViewModel::class.java)

        myProductsAdapter = MyProductsAdapter()
        binding.rvUsersProducts.adapter = myProductsAdapter

        appViewModel.getUsersProducts("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg5ODIzNTQ1LCJpYXQiOjE2ODkzOTE1NDUsImp0aSI6IjNmNDBhZTIxOTRiMjQ2YjFiOTdiODA4NDhmMjliODllIiwidXNlcl9pZCI6MTU4fQ.FvJu6ND6sHW2pBNXb8cEn_DKY4ruXwqMCSkGt6C7k6Q")
            .observe(requireActivity()){
                when(it.status){
                    Status.LOADING ->{
                        Log.d(TAG, "onCreate: Loading")
                        binding.progressUserProducts.visibility = View.VISIBLE
                    }
                    Status.ERROR->{
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
}