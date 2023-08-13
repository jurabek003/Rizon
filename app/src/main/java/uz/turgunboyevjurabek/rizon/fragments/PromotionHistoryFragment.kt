package uz.turgunboyevjurabek.rizon.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.turgunboyevjurabek.rizon.R
import uz.turgunboyevjurabek.rizon.databinding.FragmentPromotionHistoryBinding
import uz.turgunboyevjurabek.rizon.databinding.FragmentPurchaseHistoryBinding


class PromotionHistoryFragment : Fragment() {
    private val binding by lazy { FragmentPromotionHistoryBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.imgBackHistory.setOnClickListener {
            findNavController().popBackStack(R.id.promotionFragment,true)
        }
    }
}