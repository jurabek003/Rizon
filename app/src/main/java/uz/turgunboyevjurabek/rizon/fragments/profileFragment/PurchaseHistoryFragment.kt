package uz.turgunboyevjurabek.rizon.fragments.profileFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.turgunboyevjurabek.rizon.R
import uz.turgunboyevjurabek.rizon.adapters.PurchaseHistoryAdapter
import uz.turgunboyevjurabek.rizon.databinding.FragmentPurchaseHistoryBinding
import uz.turgunboyevjurabek.rizon.madels.PurchaseHistory
import uz.turgunboyevjurabek.rizon.utils.AppObject


class PurchaseHistoryFragment : Fragment() {
    private lateinit var purchaseHistoryAdapter: PurchaseHistoryAdapter
    private lateinit var list: ArrayList<PurchaseHistory>
    private val binding by lazy { FragmentPurchaseHistoryBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        list= ArrayList()
        list.add(PurchaseHistory("11.02.2023","123 456 789"))
        list.add(PurchaseHistory("11.02.2023","123 456 789"))
        list.add(PurchaseHistory("11.02.2023","123 456 789"))
        list.add(PurchaseHistory("11.02.2023","123 456 789"))
        list.add(PurchaseHistory("11.02.2023","123 456 789"))
        list.add(PurchaseHistory("11.02.2023","123 456 789"))
        list.add(PurchaseHistory("11.02.2023","123 456 789"))
        list.add(PurchaseHistory("11.02.2023","123 456 789"))
        list.add(PurchaseHistory("11.02.2023","123 456 789"))
        list.add(PurchaseHistory("11.02.2023","123 456 789"))
        list.add(PurchaseHistory("11.02.2023","123 456 789"))
        list.add(PurchaseHistory("11.02.2023","123 456 789"))
        purchaseHistoryAdapter= PurchaseHistoryAdapter(list)
        binding.rvHistory.adapter=purchaseHistoryAdapter

        AppObject.binding.thtPanel.text="Harid tarixi"
        return binding.root
    }
}