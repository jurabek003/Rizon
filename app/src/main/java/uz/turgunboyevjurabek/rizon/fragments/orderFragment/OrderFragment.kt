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
import uz.turgunboyevjurabek.rizon.utils.AppObject
import uz.turgunboyevjurabek.rizon.utils.MySharedPreference
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
        MySharedPreference.init(binding.root.context)

        ordersViewModel = ViewModelProvider(requireActivity())[OrdersViewModel::class.java]

        ordersViewModel.getUsersOrders(MySharedPreference.token)
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
                        userOrderAdapter.list.clear()
                        userOrderAdapter.list.addAll(it.data?.orders!!)
                        userOrderAdapter.notifyDataSetChanged()
                        binding.progressUserProducts.visibility = View.GONE
                    }
                }
            }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        AppObject.binding.thtPanel.text = "Buyurtmalar"
        AppObject.binding.materialCardViewCalendar.visibility = View.INVISIBLE
    }

}