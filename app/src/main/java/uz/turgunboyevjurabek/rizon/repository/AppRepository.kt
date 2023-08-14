package uz.turgunboyevjurabek.rizon.repository

import uz.turgunboyevjurabek.rizon.madels.auth.PostAuthRequest
import uz.turgunboyevjurabek.rizon.madels.usersProducts.post.PostProductsOrder
import uz.turgunboyevjurabek.rizon.retrofit.ApiService

class AppRepository(val apiService: ApiService) {
    suspend fun getToken(postAuthRequest: PostAuthRequest) = apiService.getToken(postAuthRequest)
    suspend fun getUsersProducts(token:String) = apiService.getUsersProducts("Bearer $token")
    suspend fun getUsersOrders(token:String) = apiService.getUsersOrders("Bearer $token")

    suspend fun getAllFilials(token:String) = apiService.getFilials("Bearer $token")
    suspend fun postProductsOrder(token: String, postOrderProducts:PostProductsOrder) = apiService.postProductOrder(token, postOrderProducts)

    suspend fun getUsersProfile(token:String, month:String) = apiService.getUsersProfile("Bearer $token", month)
    suspend fun getSalaryPayments(token: String) = apiService.getSalaryPayments("Bearer $token")
    suspend fun getNotifications(token: String) = apiService.getNotifications("Bearer $token")
    suspend fun getUserCoupon(token: String) = apiService.getUsersCoupon("Bearer $token")
    suspend fun getUserMain(token: String) = apiService.getUsersMain("Bearer $token")
}