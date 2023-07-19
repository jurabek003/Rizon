package uz.turgunboyevjurabek.rizon.fragments.profileFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.turgunboyevjurabek.rizon.R
import uz.turgunboyevjurabek.rizon.adapters.SalaryHistoryAdapter
import uz.turgunboyevjurabek.rizon.databinding.FragmentSalaryHistorykBinding
import uz.turgunboyevjurabek.rizon.madels.SalaryHistory
import uz.turgunboyevjurabek.rizon.utils.AppObject

class SalaryHistoryFragment : Fragment() {
    private val binding by lazy { FragmentSalaryHistorykBinding.inflate(layoutInflater) }
    private lateinit var salaryHistoryAdapter: SalaryHistoryAdapter
    private lateinit var list: ArrayList<SalaryHistory>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        list= ArrayList()
        list.add(SalaryHistory("sadasd",123))
        list.add(SalaryHistory("sadasd",123))
        list.add(SalaryHistory("sadasd",123))
        list.add(SalaryHistory("sadasd",123))
        list.add(SalaryHistory("sadasd",123))
        list.add(SalaryHistory("sadasd",123))
        list.add(SalaryHistory("sadasd",123))
        list.add(SalaryHistory("sadasd",123))
        list.add(SalaryHistory("sadasd",123))
        list.add(SalaryHistory("sadasd",123))
        list.add(SalaryHistory("sadasd",123))
        list.add(SalaryHistory("sadasd",123))
        list.add(SalaryHistory("sadasd",123))
        list.add(SalaryHistory("sadasd",123))
        list.add(SalaryHistory("sadasd",123))
        list.add(SalaryHistory("sadasd",123))
        list.add(SalaryHistory("sadasd",123))
        list.add(SalaryHistory("sadasd",123))
        list.add(SalaryHistory("sadasd",123))
        list.add(SalaryHistory("sadasd",123))
        salaryHistoryAdapter= SalaryHistoryAdapter(list)
        binding.rvSalary.adapter=salaryHistoryAdapter


        AppObject.binding.thtPanel.text = "Maosh tarixi"
        return binding.root
    }
}