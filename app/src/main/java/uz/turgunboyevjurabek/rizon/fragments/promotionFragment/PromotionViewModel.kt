package uz.turgunboyevjurabek.rizon.fragments.promotionFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.turgunboyevjurabek.rizon.madels.promotion.GetPromotionResponse
import uz.turgunboyevjurabek.rizon.madels.userOrders.GetUsersOrdersResponse
import uz.turgunboyevjurabek.rizon.repository.MyViewModelObjects
import uz.turgunboyevjurabek.rizon.utils.Resource

class PromotionViewModel : ViewModel() {
    private val liveData = MutableLiveData<Resource<GetPromotionResponse>>()
    fun getPromotions(token: String): MutableLiveData<Resource<GetPromotionResponse>> {
        viewModelScope.launch {
            liveData.postValue(Resource.loading("loading..."))
            try {
                coroutineScope {
                    val promotions =
                        withContext(Dispatchers.IO) {
                            MyViewModelObjects.appRepository.getPromotions(token)
                        }
                    liveData.postValue(Resource.success(promotions))
                }
            }catch (e:Exception){
                liveData.postValue(Resource.error(e.message))
            }
        }
        return liveData
    }
}