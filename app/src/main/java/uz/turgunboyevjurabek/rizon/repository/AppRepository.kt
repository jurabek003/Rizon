package uz.turgunboyevjurabek.rizon.repository

import uz.turgunboyevjurabek.rizon.retrofit.ApiService

class AppRepository(val apiService: ApiService) {
    suspend fun getUsersProducts(token:String) = apiService.getUsersProducts("Bearer $token")
    suspend fun getUsersOrders(token:String) = apiService.getUsersOrders("Bearer $token")
    suspend fun getUsersProfile(token:String, month:String) = apiService.getUsersProfile("Bearer $token", month)
    suspend fun getSalaryPayments(token: String) = apiService.getSalaryPayments("Bearer $token")
    suspend fun getNotifications(token: String) = apiService.getNotifications("Bearer $token")
    suspend fun getUserCoupon(token: String) = apiService.getUsersCoupon("Bearer $token")
    suspend fun getUserMain(token: String) = apiService.getUsersMain("Bearer $token")
}