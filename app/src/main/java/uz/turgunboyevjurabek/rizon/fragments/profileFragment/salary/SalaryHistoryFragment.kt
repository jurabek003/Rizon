package uz.turgunboyevjurabek.rizon.fragments.profileFragment.salary

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import uz.turgunboyevjurabek.rizon.adapters.SalaryHistoryAdapter
import uz.turgunboyevjurabek.rizon.adapters.SelectSalary
import uz.ilhomjon.rizonuz.databinding.DialogSalaryItemBinding
import uz.ilhomjon.rizonuz.databinding.FragmentSalaryHistorykBinding
import uz.turgunboyevjurabek.rizon.madels.salary.SalaryHistory
import uz.turgunboyevjurabek.rizon.utils.AppObject
import uz.turgunboyevjurabek.rizon.utils.MySharedPreference
import uz.turgunboyevjurabek.rizon.utils.Status

private const val TAG = "SalaryHistoryFragment"
class SalaryHistoryFragment : Fragment(),SelectSalary {
    private val binding by lazy { FragmentSalaryHistorykBinding.inflate(layoutInflater) }
    private lateinit var salaryHistoryAdapter: SalaryHistoryAdapter
    lateinit var salaryViewModel: SalaryViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        salaryHistoryAdapter= SalaryHistoryAdapter(this)
        binding.rvSalary.adapter=salaryHistoryAdapter
        MySharedPreference.init(binding.root.context)

        salaryViewModel = ViewModelProvider(requireActivity())[SalaryViewModel::class.java]
        salaryViewModel.getSalary(MySharedPreference.token)
            .observe(requireActivity()){
                when(it.status){
                    Status.ERROR->{
                        Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "onCreateView: ${it.message}")
                        binding.myProgressBar.visibility = View.GONE
                    }
                    Status.LOADING->{
                        binding.myProgressBar.visibility = View.VISIBLE
                    }
                    Status.SUCCESS->{
                        binding.myProgressBar.visibility = View.GONE
                        salaryHistoryAdapter.list.clear()
                        salaryHistoryAdapter.list.addAll(it.data?.salary_history!!)
                        salaryHistoryAdapter.notifyDataSetChanged()
                    }
                }
            }


        AppObject.binding.thtPanel.text = "Maosh tarixi"
        AppObject.binding.materialCardViewCalendar.visibility = View.INVISIBLE
        return binding.root
    }

    override fun clickSalary(salaryHistory: SalaryHistory, position: Int) {
        val dialog=MaterialAlertDialogBuilder(requireContext()).create()
        val dialogSalaryItemBinding=DialogSalaryItemBinding.inflate(layoutInflater)
        dialog.setView(dialogSalaryItemBinding.root)
        dialog.show()

    }
}