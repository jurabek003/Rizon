package uz.turgunboyevjurabek.rizon.fragments.productsFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.turgunboyevjurabek.rizon.madels.usersProducts.GetUserProductsResponse
import uz.turgunboyevjurabek.rizon.utils.Resource
import uz.turgunboyevjurabek.rizon.repository.MyViewModelObjects.appRepository

class ProductsViewModel : ViewModel() {
    private val userAllProducts = MutableLiveData<Resource<GetUserProductsResponse>>()
    fun getUsersProducts(token:String): MutableLiveData<Resource<GetUserProductsResponse>> {
        viewModelScope.launch {
            userAllProducts.postValue(Resource.loading("loading..."))
            try {
                coroutineScope {
                    val userProducts =
                        withContext(Dispatchers.IO) {
                            appRepository.getUsersProducts(token)
                        }
                    userAllProducts.postValue(Resource.success(userProducts))
                }
            }catch (e:Exception){
                userAllProducts.postValue(Resource.error(e.message))
            }
        }
        return userAllProducts
    }
}