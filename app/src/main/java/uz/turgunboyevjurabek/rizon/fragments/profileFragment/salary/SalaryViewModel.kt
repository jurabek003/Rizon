package uz.turgunboyevjurabek.rizon.fragments.profileFragment.salary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.turgunboyevjurabek.rizon.madels.salary.GetSalaryResponce
import uz.turgunboyevjurabek.rizon.repository.MyViewModelObjects
import uz.turgunboyevjurabek.rizon.utils.Resource

class SalaryViewModel : ViewModel() {

    private val liveData = MutableLiveData<Resource<GetSalaryResponce>>()
    fun getSalary(token:String):LiveData<Resource<GetSalaryResponce>>{

        viewModelScope.launch {
            liveData.postValue(Resource.loading("loading..."))
            try {
                coroutineScope {
                    val salary =
                        withContext(Dispatchers.IO) {
                            MyViewModelObjects.appRepository.getSalaryPayments(token)
                        }
                    liveData.postValue(Resource.success(salary))
                }
            }catch (e:Exception){
                liveData.postValue(Resource.error(e.message))
            }
        }

        return liveData
    }
}