package uz.turgunboyevjurabek.rizon.fragments.homeFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.turgunboyevjurabek.rizon.madels.UserMain.GetUsersMainResponse
import uz.turgunboyevjurabek.rizon.repository.MyViewModelObjects
import uz.turgunboyevjurabek.rizon.utils.Resource

class HomeViewModel : ViewModel() {
    private val liveData = MutableLiveData<Resource<GetUsersMainResponse>>()
    fun getUsersMain(token: String): MutableLiveData<Resource<GetUsersMainResponse>> {
        viewModelScope.launch {
            liveData.postValue(Resource.loading("loading..."))
            try {
                coroutineScope {
                    val usersMain =
                        withContext(Dispatchers.IO) {
                            MyViewModelObjects.appRepository.getUserMain(token)
                        }
                    liveData.postValue(Resource.success(usersMain))
                }
            }catch (e:Exception){
                liveData.postValue(Resource.error(e.message))
            }
        }
        return liveData
    }
}