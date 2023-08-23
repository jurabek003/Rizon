package uz.turgunboyevjurabek.rizon.fragments.orderFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.turgunboyevjurabek.rizon.madels.userOrders.GetUsersOrdersResponse
import uz.turgunboyevjurabek.rizon.utils.Resource
import uz.turgunboyevjurabek.rizon.repository.MyViewModelObjects.appRepository

class OrdersViewModel:ViewModel() {
    private val userOrders = MutableLiveData<Resource<GetUsersOrdersResponse>>()
    fun getUsersOrders(token: String): MutableLiveData<Resource<GetUsersOrdersResponse>> {
        viewModelScope.launch {
            userOrders.postValue(Resource.loading("loading..."))
            try {
                coroutineScope {
                    val userOrder =
                        withContext(Dispatchers.IO) {
                            appRepository.getUsersOrders(token)
                        }
                    userOrders.postValue(Resource.success(userOrder))
                }
            }catch (e:Exception){
                userOrders.postValue(Resource.error(e.message))
            }
        }
        return userOrders
    }

    private val deleteLiveData = MutableLiveData<Resource<Int>>()
    fun deleteOrder(token: String, id:String):MutableLiveData<Resource<Int>>{
        viewModelScope.launch {
            deleteLiveData.postValue(Resource.loading("loading..."))
            try {
                coroutineScope {
                        launch (Dispatchers.IO) {
                            appRepository.deleteProductsOrder(token, id)
                        }
                    deleteLiveData.postValue(Resource.success(204))
                }
            }catch (e:Exception){
                deleteLiveData.postValue(Resource.error(e.message))
            }
        }

        return deleteLiveData
    }

}