package uz.turgunboyevjurabek.rizon.fragments.productsFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.turgunboyevjurabek.rizon.madels.filial.Filial
import uz.turgunboyevjurabek.rizon.madels.usersProducts.GetUserProductsResponse
import uz.turgunboyevjurabek.rizon.madels.usersProducts.post.PostProductsOrder
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

    private val filialLiveData = MutableLiveData<Resource<ArrayList<Filial>>>()
    fun getAllFilial(token: String):MutableLiveData<Resource<ArrayList<Filial>>>{
        viewModelScope.launch {
            filialLiveData.postValue(Resource.loading("loading..."))
            try {
                coroutineScope {
                    val userProducts =
                        withContext(Dispatchers.IO) {
                            appRepository.getAllFilials(token)
                        }
                    filialLiveData.postValue(Resource.success(userProducts))
                }
            }catch (e:Exception){
                filialLiveData.postValue(Resource.error(e.message))
            }
        }
        return filialLiveData
    }

    private val postLiveData = MutableLiveData<Resource<PostProductsOrder>>()
    fun postProductsOrder(token:String, postOrderProducts: PostProductsOrder):LiveData<Resource<PostProductsOrder>>{
        viewModelScope.launch {
            postLiveData.postValue(Resource.loading("loading..."))
            try {
                coroutineScope {
                    val userProducts =
                        withContext(Dispatchers.IO) {
                            appRepository.postProductsOrder(token, postOrderProducts)
                        }
                    postLiveData.postValue(Resource.success(userProducts))
                }
            }catch (e:Exception){
                postLiveData.postValue(Resource.error(e.message))
            }
        }
        return postLiveData
    }
}