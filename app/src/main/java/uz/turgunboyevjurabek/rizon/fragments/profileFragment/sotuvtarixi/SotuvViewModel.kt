package uz.turgunboyevjurabek.rizon.fragments.profileFragment.sotuvtarixi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.turgunboyevjurabek.rizon.madels.userOrders.GetUsersOrdersResponse
import uz.turgunboyevjurabek.rizon.repository.MyViewModelObjects
import uz.turgunboyevjurabek.rizon.utils.Resource

class SotuvViewModel :ViewModel(){
    private val userOrders = MutableLiveData<Resource<GetUsersOrdersResponse>>()
    fun getUsersOrders(token: String): MutableLiveData<Resource<GetUsersOrdersResponse>> {
        viewModelScope.launch {
            userOrders.postValue(Resource.loading("loading..."))
            try {
                coroutineScope {
                    val userOrder =
                        withContext(Dispatchers.IO) {
                            MyViewModelObjects.appRepository.getUsersOrders(token)
                        }
                    userOrders.postValue(Resource.success(userOrder))
                }
            }catch (e:Exception){
                userOrders.postValue(Resource.error(e.message))
            }
        }
        return userOrders
    }

}