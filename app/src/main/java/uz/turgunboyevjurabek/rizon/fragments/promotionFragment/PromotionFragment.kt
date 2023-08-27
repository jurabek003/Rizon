package uz.turgunboyevjurabek.rizon.fragments.promotionFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import uz.ilhomjon.rizonuz.R
import uz.turgunboyevjurabek.rizon.adapters.viewPagerAdapter.ViewPagerAdapter
import uz.ilhomjon.rizonuz.databinding.FragmentPromotionBinding
import uz.ilhomjon.rizonuz.databinding.ItemTabBinding
import uz.turgunboyevjurabek.rizon.fragments.orderFragment.OrdersViewModel
import uz.turgunboyevjurabek.rizon.madels.promotion.pager.Pager
import uz.turgunboyevjurabek.rizon.madels.promotion.GetPromotionResponse
import uz.turgunboyevjurabek.rizon.utils.AppObject
import uz.turgunboyevjurabek.rizon.utils.MySharedPreference
import uz.turgunboyevjurabek.rizon.utils.Status

private const val TAG = "PromotionFragment"
class PromotionFragment : Fragment() {
    private val binding by lazy { FragmentPromotionBinding.inflate(layoutInflater) }
    lateinit var viewPager2Adapter: ViewPagerAdapter
    lateinit var list: ArrayList<Pager>
    lateinit var promotionViewModel: PromotionViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment

        return binding.root
    }

    private fun viewPager(getPromotionResponse: GetPromotionResponse) {
        list= ArrayList()
        list.addAll(listOf(Pager("Junior"), Pager("Middle"), Pager("Senior")))

        viewPager2Adapter=ViewPagerAdapter(AppObject.fragmentManager, getPromotionResponse)
        binding.myViewPager.adapter=viewPager2Adapter
//        binding.myViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))
        binding.tabLayout.setupWithViewPager(binding.myViewPager)
//
//        val tabCount=binding.tabLayout.tabCount
//
//        for (i in 0 until tabCount){
//            val itemTabBinding = ItemTabBinding.inflate(layoutInflater)
//            val tab=binding.tabLayout.getTabAt(i)
//            itemTabBinding.tabItemName.text=list[i].name.toString()
//            tab?.setCustomView(itemTabBinding.root)
//        }

//     binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
//            @SuppressLint("ResourceAsColor")
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//
////                val tabBinding = ItemTabBinding.bind(tab?.customView!!)
////                tabBinding.tabItemName.alpha=1f
//                tab?.
//                view?.
//                findViewById<TextView>(R.id.tab_item_name)!!
//                    .alpha = 1f
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
////                val tabBinding = ItemTabBinding.bind(tab?.customView!!)
////                tabBinding.tabItemName.alpha=.5f
//                tab?.view?.findViewById<TextView>(R.id.tab_item_name)!!
//                    .alpha = 0.5f
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//
//            }
//        })



    }

    override fun onResume() {
        super.onResume()
        AppObject.binding.thtPanel.text = "Promotion"
        AppObject.binding.materialCardViewCalendar.visibility = View.INVISIBLE
        binding.btnHistory.setOnClickListener {
            findNavController().navigate(R.id.promotionHistoryFragment)
        }


        promotionViewModel = ViewModelProvider(requireActivity())[PromotionViewModel::class.java]

        promotionViewModel.getPromotions(MySharedPreference.token)
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
                        viewPager(it.data!!)
                        binding.myProgressBar.visibility = View.GONE
                    }
                }
            }

    }
}

