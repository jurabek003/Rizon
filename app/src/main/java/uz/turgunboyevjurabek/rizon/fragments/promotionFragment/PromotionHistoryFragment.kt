package uz.turgunboyevjurabek.rizon.fragments.promotionFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.ilhomjon.rizonuz.databinding.FragmentPromotionHistoryBinding
import uz.turgunboyevjurabek.rizon.adapters.PromotionBuyHistoryAdapter
import uz.turgunboyevjurabek.rizon.adapters.PurchaseHistoryAdapter
import uz.turgunboyevjurabek.rizon.utils.MySharedPreference
import uz.turgunboyevjurabek.rizon.utils.Status

private const val TAG = "PromotionHistoryFragmen"
class PromotionHistoryFragment : Fragment() {
    private val binding by lazy { FragmentPromotionHistoryBinding.inflate(layoutInflater) }
    private lateinit var promotionViewModel:PromotionViewModel
    private lateinit var promotionHistoryAdapter: PromotionBuyHistoryAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        promotionViewModel = ViewModelProvider(requireActivity())[PromotionViewModel::class.java]
        promotionHistoryAdapter = PromotionBuyHistoryAdapter()
        binding.tvRv.adapter = promotionHistoryAdapter


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.imgBackHistory.setOnClickListener {
            findNavController().popBackStack()
        }

        promotionViewModel.getHistory(MySharedPreference.token)
            .observe(requireActivity()){
                when(it.status){
                    Status.LOADING ->{
                        Log.d(TAG, "onCreate: Loading")
                        binding.myProgressBar.visibility = View.VISIBLE
                    }
                    Status.ERROR->{
                        Log.d(TAG, "onCreate: Error ${it.message}")
                        binding.myProgressBar.visibility = View.GONE
                        Toast.makeText(context, "Error ${it.message}", Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS ->{
                        Log.d(TAG, "onCreate: ${it.data}")
                        binding.myProgressBar.visibility = View.GONE
                        promotionHistoryAdapter.list.clear()
                        if (it.data?.purchases!=null)
                            promotionHistoryAdapter.list.addAll(it.data?.purchases)
                        else Toast.makeText(
                            context,
                            "Hozircha xaridlar tarixi bo'sh",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

    }
}