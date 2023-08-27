package uz.turgunboyevjurabek.rizon.fragments.promotionFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.turgunboyevjurabek.rizon.madels.promotion.GetPromotionBuyHistoryResponse
import uz.turgunboyevjurabek.rizon.madels.promotion.GetPromotionResponse
import uz.turgunboyevjurabek.rizon.madels.promotion.post.PostBuyPromotionRequest
import uz.turgunboyevjurabek.rizon.madels.promotion.post.PostBuyPromotionResponse
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

    private val liveDataPH = MutableLiveData<Resource<GetPromotionBuyHistoryResponse>>()
    fun getHistory(token: String): MutableLiveData<Resource<GetPromotionBuyHistoryResponse>> {
        viewModelScope.launch {
            liveDataPH.postValue(Resource.loading("loading..."))
            try {
                coroutineScope {
                    val promotions =
                        withContext(Dispatchers.IO) {
                            MyViewModelObjects.appRepository.getPromotionBuyHistory(token)
                        }
                    liveDataPH.postValue(Resource.success(promotions))
                }
            }catch (e:Exception){
                liveDataPH.postValue(Resource.error(e.message))
            }
        }
        return liveDataPH
    }
    private val liveDataP = MutableLiveData<Resource<PostBuyPromotionResponse>>()
    fun postBuyPromotion(token: String, postBuyPromotionRequest: PostBuyPromotionRequest): MutableLiveData<Resource<PostBuyPromotionResponse>> {
        viewModelScope.launch {
            liveDataP.postValue(Resource.loading("loading..."))
            try {
                coroutineScope {
                    val promotions =
                        withContext(Dispatchers.IO) {
                            MyViewModelObjects.appRepository.postBuyPromotion(token, postBuyPromotionRequest)
                        }
                    liveDataP.postValue(Resource.success(promotions))
                }
            } catch (e: Exception) {
                liveDataP.postValue(Resource.error(e.message))
            }
        }
        return liveDataP
    }
}