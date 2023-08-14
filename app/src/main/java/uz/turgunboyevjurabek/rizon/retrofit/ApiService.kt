package uz.turgunboyevjurabek.rizon.retrofit

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import uz.turgunboyevjurabek.rizon.madels.UserMain.GetUsersMainResponse
import uz.turgunboyevjurabek.rizon.madels.auth.PostAuthRequest
import uz.turgunboyevjurabek.rizon.madels.auth.PostAuthResponse
import uz.turgunboyevjurabek.rizon.madels.coupon.GetUsersCoupon
import uz.turgunboyevjurabek.rizon.madels.notification.GetNotificationResponse
import uz.turgunboyevjurabek.rizon.madels.salary.GetSalaryResponce
import uz.turgunboyevjurabek.rizon.madels.userOrders.GetUsersOrdersResponse
import uz.turgunboyevjurabek.rizon.madels.usersProducts.GetUserProductsResponse
import uz.turgunboyevjurabek.rizon.madels.usersProducts.post.PostOrderProducts
import uz.turgunboyevjurabek.rizon.madels.usersProfile.GetUserProfileResponse

interface ApiService {

    //    token olish authorization
    @POST("token/")
    suspend fun getToken(@Body postAuthRequest: PostAuthRequest): PostAuthResponse


    //    @Headers({"Authorization", "Bearer $token"})
    @GET("users-products/")
    suspend fun getUsersProducts(@Header("Authorization") token: String): GetUserProductsResponse

    @POST("users/product/order/")
    suspend fun postProductOrder(
        @Header("Authorization") token: String,
        @Body postOrderProducts: PostOrderProducts
    ): GetUserProductsResponse

    @GET("users-orders/")
    suspend fun getUsersOrders(@Header("Authorization") token: String): GetUsersOrdersResponse

    @GET("users-profil/month/{month}")
    suspend fun getUsersProfile(
        @Header("Authorization") token: String,
        @Path("month") month: String
    ): GetUserProfileResponse

    @GET("users-salary/payments/")
    suspend fun getSalaryPayments(@Header("Authorization") token: String): GetSalaryResponce

    @GET("users-notifications")
    suspend fun getNotifications(@Header("Authorization") token: String): GetNotificationResponse

    @GET("users-coupons")
    suspend fun getUsersCoupon(@Header("Authorization") token: String): GetUsersCoupon

    @GET("users-main-a")
    suspend fun getUsersMain(@Header("Authorization") token: String): GetUsersMainResponse

}
