package uz.turgunboyevjurabek.rizon.fragments.orderFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import uz.turgunboyevjurabek.rizon.R
import uz.turgunboyevjurabek.rizon.adapters.UserOrderAdapter
import uz.turgunboyevjurabek.rizon.databinding.FragmentOrderBinding
import uz.turgunboyevjurabek.rizon.utils.Status

private const val TAG = "OrderFragment"
class OrderFragment : Fragment() {
    private val binding by lazy { FragmentOrderBinding.inflate(layoutInflater) }
    private lateinit var userOrderAdapter: UserOrderAdapter
    private lateinit var ordersViewModel: OrdersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
      userOrderAdapter = UserOrderAdapter()
        binding.rvUsersOrders.adapter = userOrderAdapter

        ordersViewModel = ViewModelProvider(requireActivity())[OrdersViewModel::class.java]

        ordersViewModel.getUsersOrders("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg5ODIzNTQ1LCJpYXQiOjE2ODkzOTE1NDUsImp0aSI6IjNmNDBhZTIxOTRiMjQ2YjFiOTdiODA4NDhmMjliODllIiwidXNlcl9pZCI6MTU4fQ.FvJu6ND6sHW2pBNXb8cEn_DKY4ruXwqMCSkGt6C7k6Q")
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
                        userOrderAdapter.list.addAll(it.data?.orders!!)
                        userOrderAdapter.notifyDataSetChanged()
                        binding.progressUserProducts.visibility = View.GONE
                    }
                }
            }

        return binding.root
    }

}