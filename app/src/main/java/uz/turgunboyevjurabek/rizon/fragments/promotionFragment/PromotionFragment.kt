package uz.turgunboyevjurabek.rizon.fragments.promotionFragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import uz.turgunboyevjurabek.rizon.R
import uz.turgunboyevjurabek.rizon.adapters.viewPagerAdapter.ViewPager2Adapter
import uz.turgunboyevjurabek.rizon.databinding.FragmentPromotionBinding
import uz.turgunboyevjurabek.rizon.databinding.ItemTabBinding
import uz.turgunboyevjurabek.rizon.madels.pager.Pager

class PromotionFragment : Fragment() {
    private val binding by lazy { FragmentPromotionBinding.inflate(layoutInflater) }
    lateinit var viewPager2Adapter: ViewPager2Adapter
    lateinit var list: ArrayList<Pager>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment

        viewPager2()

        return binding.root
    }

    private fun viewPager2() {
        list= ArrayList()
        list.addAll(listOf(Pager("Junior"),Pager("Middle"),Pager("Senior")))

        viewPager2Adapter=ViewPager2Adapter(parentFragmentManager)
        binding.myViewPager2.adapter=viewPager2Adapter
        binding.tabLayout.setupWithViewPager(binding.myViewPager2)


        val tabCount=binding.tabLayout.tabCount


        for (i in 0 until tabCount){
            val itemTabBinding = ItemTabBinding.inflate(layoutInflater)
            val tab=binding.tabLayout.getTabAt(i)
            itemTabBinding.tabItemName.text=list[i].name.toString()
            tab?.text=itemTabBinding.tabItemName.text.toString()
            tab?.setCustomView(itemTabBinding.root)
        }
        val itemTabBinding=ItemTabBinding.inflate(layoutInflater)
     /*  binding.tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{

            @SuppressLint("ResourceAsColor")
            override fun onTabSelected(tab: TabLayout.Tab?) {

                itemTabBinding.tabItemName.text=list[tab?.position!!].name.toString()
                itemTabBinding.tabItemName.alpha=1f
                tab.text=itemTabBinding.tabItemName.text.toString()
                tab.customView=itemTabBinding.tabItemName
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

                itemTabBinding.tabItemName.text=list[tab?.position!!].name.toString()
                tab.text=itemTabBinding.tabItemName.text.toString()
                tab.customView=itemTabBinding.tabItemName
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Toast.makeText(requireContext(), "reselected", Toast.LENGTH_SHORT).show()
            }
        })
*/



    }
}

