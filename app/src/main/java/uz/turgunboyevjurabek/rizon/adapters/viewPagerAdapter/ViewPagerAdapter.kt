package uz.turgunboyevjurabek.rizon.adapters.viewPagerAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import uz.turgunboyevjurabek.rizon.fragments.promotionFragment.viewPagerFragment.JuniorFragment
import uz.turgunboyevjurabek.rizon.madels.promotion.GetPromotionResponse

class ViewPagerAdapter(fm:FragmentManager, val getPromotionResponse: GetPromotionResponse): FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0->{

                return JuniorFragment.newInstance(getPromotionResponse.small_interval)
            }
            1->{

                return JuniorFragment.newInstance(getPromotionResponse.middle_interval)
            }
            2->{

                return JuniorFragment.newInstance(getPromotionResponse.large_interval)
            }
        }
        return JuniorFragment.newInstance(getPromotionResponse.small_interval)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0->"Junior"
            1->"Middle"
            2->"Senior"
            else->"Junior"
        }
    }
}