package uz.turgunboyevjurabek.rizon.fragments.profileFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.turgunboyevjurabek.rizon.madels.usersProfile.GetUserProfileResponse
import uz.turgunboyevjurabek.rizon.madels.usersProfile.userChangeInfo.PatchUserChangeInfoRequest
import uz.turgunboyevjurabek.rizon.madels.usersProfile.userChangeInfo.PatchUserChangeInfoResponse
import uz.turgunboyevjurabek.rizon.repository.MyViewModelObjects
import uz.turgunboyevjurabek.rizon.utils.Resource

class ProfileViewModel : ViewModel() {
    private val liveData = MutableLiveData<Resource<GetUserProfileResponse>>()
    fun getUsersProfile(token:String, month:String): MutableLiveData<Resource<GetUserProfileResponse>> {
        //month: 2023-07
        viewModelScope.launch {
            liveData.postValue(Resource.loading("loading..."))
            try {
                coroutineScope {
                    val profile =
                        withContext(Dispatchers.IO) {
                            MyViewModelObjects.appRepository.getUsersProfile(token, month)
                        }
                    liveData.postValue(Resource.success(profile))
                }
            }catch (e:Exception){
                liveData.postValue(Resource.error(e.message))
            }
        }
        return liveData
    }

    private val patchLiveData = MutableLiveData<Resource<PatchUserChangeInfoResponse>>()
    fun changeUserInfo(token: String, patchUserChangeInfoRequest: PatchUserChangeInfoRequest):MutableLiveData<Resource<PatchUserChangeInfoResponse>>{
        viewModelScope.launch {
            patchLiveData.postValue(Resource.loading("loading..."))
            try {
                coroutineScope {
                    val profile =
                        withContext(Dispatchers.IO) {
                            MyViewModelObjects.appRepository.changeUserInfo(token, patchUserChangeInfoRequest)
                        }
                    patchLiveData.postValue(Resource.success(profile))
                }
            }catch (e:Exception){
                patchLiveData.postValue(Resource.error(e.message))
            }
        }

        return patchLiveData
    }
}