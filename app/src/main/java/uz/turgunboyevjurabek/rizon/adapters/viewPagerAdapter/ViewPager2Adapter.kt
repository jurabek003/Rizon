package uz.turgunboyevjurabek.rizon.adapters.viewPagerAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.turgunboyevjurabek.rizon.fragments.viewPagerFragment.JuniorFragment
import uz.turgunboyevjurabek.rizon.fragments.viewPagerFragment.MiddleFragment
import uz.turgunboyevjurabek.rizon.fragments.viewPagerFragment.SeniorFragment

class ViewPager2Adapter(fm:FragmentManager): FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0->{
                return JuniorFragment()
            }
            1->{
                return MiddleFragment()
            }
            2->{
                return SeniorFragment()
            }
        }
        return JuniorFragment()
    }
}