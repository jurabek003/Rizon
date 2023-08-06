package uz.turgunboyevjurabek.rizon.fragments.profileFragment.sotuvtarixi

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import uz.turgunboyevjurabek.rizon.adapters.PurchaseHistoryAdapter
import uz.turgunboyevjurabek.rizon.databinding.FragmentPurchaseHistoryBinding
import uz.turgunboyevjurabek.rizon.utils.AppObject
import uz.turgunboyevjurabek.rizon.utils.MySharedPreference
import uz.turgunboyevjurabek.rizon.utils.Status

private const val TAG = "PurchaseHistoryFragment"
class PurchaseHistoryFragment : Fragment() {
    private lateinit var purchaseHistoryAdapter: PurchaseHistoryAdapter
    private lateinit var sotuvViewModel: SotuvViewModel
    private val binding by lazy { FragmentPurchaseHistoryBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        MySharedPreference.init(binding.root.context)

        sotuvViewModel = ViewModelProvider(requireActivity())[SotuvViewModel::class.java]

        purchaseHistoryAdapter = PurchaseHistoryAdapter()
        binding.rvHistory.adapter = purchaseHistoryAdapter

        sotuvViewModel.getUsersOrders(MySharedPreference.token)
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
                        binding.progressUserProducts.visibility = View.GONE
                        purchaseHistoryAdapter.list.clear()
                        purchaseHistoryAdapter.list.addAll(it.data?.orders!!)
                        purchaseHistoryAdapter.notifyDataSetChanged()
                    }
                }
            }




        AppObject.binding.thtPanel.text="Harid tarixi"
        AppObject.binding.materialCardViewCalendar.visibility = View.INVISIBLE
        return binding.root
    }
}