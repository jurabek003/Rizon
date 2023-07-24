package uz.turgunboyevjurabek.rizon.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import uz.turgunboyevjurabek.rizon.madels.notification.GetNotificationResponse
import uz.turgunboyevjurabek.rizon.madels.salary.GetSalaryResponce
import uz.turgunboyevjurabek.rizon.madels.userOrders.GetUsersOrdersResponse
import uz.turgunboyevjurabek.rizon.madels.usersProducts.GetUserProductsResponse
import uz.turgunboyevjurabek.rizon.madels.usersProfile.GetUserProfileResponse

interface ApiService {

    //token olish authorization
//    @POST("token/")
//    fun getToken(@Body myTokenRequest: MyTokenRequest):Call<MyTokenResponce>


//    @Headers({"Authorization", "Bearer $token"})
    @GET("users-products/")
    suspend fun getUsersProducts(@Header("Authorization") token:String):GetUserProductsResponse

    @GET("users-orders/")
    suspend fun getUsersOrders(@Header("Authorization") token:String):GetUsersOrdersResponse

    @GET("users-profile/month/{month}")
    suspend fun getUsersProfile(@Header("Authorization") token:String, @Path("month") month:String):GetUserProfileResponse

    @GET("users-salary/payments/")
    suspend fun getSalaryPayments(@Header("Authorization") token: String): GetSalaryResponce

    @GET("users-notifications")
    suspend fun getNotifications(@Header("Authorization") token: String): GetNotificationResponse


}
