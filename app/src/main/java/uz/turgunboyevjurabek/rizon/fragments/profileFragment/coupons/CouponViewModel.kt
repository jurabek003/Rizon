package uz.turgunboyevjurabek.rizon.fragments.profileFragment.coupons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.turgunboyevjurabek.rizon.madels.coupon.GetUsersCoupon
import uz.turgunboyevjurabek.rizon.repository.MyViewModelObjects
import uz.turgunboyevjurabek.rizon.utils.Resource

class CouponViewModel : ViewModel() {
    private val liveData = MutableLiveData<Resource<GetUsersCoupon>>()
    fun getCoupons(token:String): LiveData<Resource<GetUsersCoupon>> {

        viewModelScope.launch {
            liveData.postValue(Resource.loading("loading..."))
            try {
                coroutineScope {
                    val coupons =
                        withContext(Dispatchers.IO) {
                            MyViewModelObjects.appRepository.getUserCoupon(token)
                        }
                    liveData.postValue(Resource.success(coupons))
                }
            }catch (e:Exception){
                liveData.postValue(Resource.error(e.message))
            }
        }

        return liveData
    }
}