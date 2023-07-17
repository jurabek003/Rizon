package uz.turgunboyevjurabek.rizon.repository

import uz.turgunboyevjurabek.rizon.retrofit.ApiService

class AppRepository(val apiService: ApiService) {
    suspend fun getUsersProducts(token:String) = apiService.getUsersProducts("Bearer $token")
    suspend fun getUsersOrders(token:String) = apiService.getUsersOrders("Bearer $token")
}