package uz.turgunboyevjurabek.rizon.fragments.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.turgunboyevjurabek.rizon.madels.auth.PostAuthRequest
import uz.turgunboyevjurabek.rizon.madels.auth.PostAuthResponse
import uz.turgunboyevjurabek.rizon.madels.usersProducts.GetUserProductsResponse
import uz.turgunboyevjurabek.rizon.repository.MyViewModelObjects
import uz.turgunboyevjurabek.rizon.utils.Resource

class AuthViewModel : ViewModel() {
    private val liveData = MutableLiveData<Resource<PostAuthResponse>>()
    fun getToken(postAuthRequest: PostAuthRequest): MutableLiveData<Resource<PostAuthResponse>> {
        viewModelScope.launch {
            liveData.postValue(Resource.loading("loading..."))
            try {
                coroutineScope {
                    val tokens =
                        withContext(Dispatchers.IO) {
                            MyViewModelObjects.appRepository.getToken(postAuthRequest)
                        }
                    liveData.postValue(Resource.success(tokens))
                }
            }catch (e:Exception){
                liveData.postValue(Resource.error(e.message))
            }
        }
        return liveData
    }
}