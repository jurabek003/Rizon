package uz.turgunboyevjurabek.rizon.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import uz.turgunboyevjurabek.rizon.madels.usersProducts.GetUserProductsResponse

interface ApiService {

    //token olish authorization
//    @POST("token/")
//    fun getToken(@Body myTokenRequest: MyTokenRequest):Call<MyTokenResponce>


//    @Headers({"Authorization", "Bearer $token"})
    @GET("users-products/")
    suspend fun getUsersProducts(@Header("Authorization") token:String):GetUserProductsResponse
}
