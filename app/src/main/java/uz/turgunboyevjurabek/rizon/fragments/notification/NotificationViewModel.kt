package uz.turgunboyevjurabek.rizon.fragments.notification

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.turgunboyevjurabek.rizon.madels.notification.GetNotificationResponse
import uz.turgunboyevjurabek.rizon.repository.MyViewModelObjects
import uz.turgunboyevjurabek.rizon.utils.Resource

class NotificationViewModel : ViewModel() {

    private val liveData = MutableLiveData<Resource<GetNotificationResponse>>()
    fun getUsersOrders(token: String): MutableLiveData<Resource<GetNotificationResponse>> {
        viewModelScope.launch {
            liveData.postValue(Resource.loading("loading..."))
            try {
                coroutineScope {
                    val notifications =
                        withContext(Dispatchers.IO) {
                            MyViewModelObjects.appRepository.getNotifications(token)
                        }
                    liveData.postValue(Resource.success(notifications))
                }
            }catch (e:Exception){
                liveData.postValue(Resource.error(e.message))
            }
        }
        return liveData
    }

}