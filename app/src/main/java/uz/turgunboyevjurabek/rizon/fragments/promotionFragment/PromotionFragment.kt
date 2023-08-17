package uz.turgunboyevjurabek.rizon.fragments.promotionFragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import uz.turgunboyevjurabek.rizon.R
import uz.turgunboyevjurabek.rizon.adapters.viewPagerAdapter.ViewPagerAdapter
import uz.turgunboyevjurabek.rizon.databinding.FragmentPromotionBinding
import uz.turgunboyevjurabek.rizon.databinding.ItemTabBinding
import uz.turgunboyevjurabek.rizon.madels.pager.Pager
import uz.turgunboyevjurabek.rizon.utils.AppObject

class PromotionFragment : Fragment() {
    private val binding by lazy { FragmentPromotionBinding.inflate(layoutInflater) }
    lateinit var viewPager2Adapter: ViewPagerAdapter
    lateinit var list: ArrayList<Pager>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewPager()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment


        return binding.root
    }

    private fun viewPager() {
        list= ArrayList()
        list.addAll(listOf(Pager("Junior"),Pager("Middle"),Pager("Senior")))

        viewPager2Adapter=ViewPagerAdapter(parentFragmentManager)
        binding.myViewPager.adapter=viewPager2Adapter
        binding.myViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))
        binding.tabLayout.setupWithViewPager(binding.myViewPager)

        val tabCount=binding.tabLayout.tabCount

        for (i in 0 until tabCount){
            val itemTabBinding = ItemTabBinding.inflate(layoutInflater)
            val tab=binding.tabLayout.getTabAt(i)
            itemTabBinding.tabItemName.text=list[i].name.toString()
            tab?.setCustomView(itemTabBinding.root)
        }

     binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            @SuppressLint("ResourceAsColor")
            override fun onTabSelected(tab: TabLayout.Tab?) {

                val tabBinding = ItemTabBinding.bind(tab?.customView!!)
                tabBinding.tabItemName.alpha=1f
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val tabBinding = ItemTabBinding.bind(tab?.customView!!)
                tabBinding.tabItemName.alpha=.5f
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Toast.makeText(requireContext(), "reselected", Toast.LENGTH_SHORT).show()
            }
        })



    }

    override fun onResume() {
        super.onResume()
        AppObject.binding.thtPanel.text = "Junior | 780 RV"
        AppObject.binding.materialCardViewCalendar.visibility = View.VISIBLE
        binding.btnHistory.setOnClickListener {
            findNavController().navigate(R.id.promotionHistoryFragment)

        }
    }
}

