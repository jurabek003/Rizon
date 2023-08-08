package uz.turgunboyevjurabek.rizon.fragments.viewPagerFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.turgunboyevjurabek.rizon.R
import uz.turgunboyevjurabek.rizon.adapters.viewPagerAdapter.RvAdapterJunior
import uz.turgunboyevjurabek.rizon.databinding.FragmentJuniorBinding
import uz.turgunboyevjurabek.rizon.madels.pager.Junior
import uz.turgunboyevjurabek.rizon.utils.AppObject

class JuniorFragment : Fragment() {
    private val binding by lazy { FragmentJuniorBinding.inflate(layoutInflater) }
    private lateinit var rvAdapterJunior: RvAdapterJunior
    private lateinit var list: ArrayList<Junior>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        adapter()


        return binding.root
    }

    private fun adapter() {
        list= ArrayList()
        list.addAll(listOf(
            Junior(""),
            Junior(""),
            Junior(""),
            Junior(""),
            Junior(""),
            Junior(""),
            Junior(""),
            Junior(""),
            Junior(""),
            Junior(""),
            Junior(""),
            Junior(""),
            Junior(""),
            Junior(""),
            Junior(""),
            Junior(""),
            Junior(""),
            Junior(""),
            Junior(""),
            Junior(""),
            Junior(""),
            Junior(""),
            Junior(""),
            Junior(""),
            Junior(""),
        ))
        rvAdapterJunior= RvAdapterJunior(list)
        binding.rvJunior.adapter=rvAdapterJunior
        rvAdapterJunior.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}