package uz.turgunboyevjurabek.rizon.fragments.promotionFragment.viewPagerFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import uz.ilhomjon.rizonuz.R
import uz.turgunboyevjurabek.rizon.adapters.viewPagerAdapter.ItemSelect
import uz.turgunboyevjurabek.rizon.adapters.viewPagerAdapter.RvAdapterJunior
import uz.ilhomjon.rizonuz.databinding.DialogPromotionBuyBinding
import uz.ilhomjon.rizonuz.databinding.FragmentJuniorBinding
import uz.turgunboyevjurabek.rizon.madels.promotion.pager.Junior
import uz.turgunboyevjurabek.rizon.madels.promotion.ProductsInterval
import uz.turgunboyevjurabek.rizon.utils.AppObject

//promotionItemFragment
class JuniorFragment : Fragment(),ItemSelect {
    private val binding by lazy { FragmentJuniorBinding.inflate(layoutInflater) }
    private lateinit var rvAdapterJunior: RvAdapterJunior
    private lateinit var list:List<ProductsInterval>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        rvAdapterJunior = RvAdapterJunior(list as ArrayList<ProductsInterval>, this)
        binding.rvJunior.adapter = rvAdapterJunior

        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun select(junior: ProductsInterval, position: Int) {
        findNavController().navigate(R.id.bioFragment, bundleOf("keyProductInterval" to junior))
    }

    override fun dialogSelect(junior: ProductsInterval, position: Int) {
        val mdialog=MaterialAlertDialogBuilder(requireContext()).create()
        val dialogPromotionBuyBinding=DialogPromotionBuyBinding.inflate(layoutInflater)
        mdialog.setView(dialogPromotionBuyBinding.root)
        mdialog.show()

        dialogPromotionBuyBinding.edtCancel.setOnClickListener {
            mdialog.cancel()
        }


    }

    companion object{
        fun newInstance(list: List<ProductsInterval>):Fragment{
            val fj = JuniorFragment()
            fj.list = list
            return fj
        }
    }
}