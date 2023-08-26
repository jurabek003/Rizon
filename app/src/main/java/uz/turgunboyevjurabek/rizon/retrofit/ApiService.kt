package uz.turgunboyevjurabek.rizon.retrofit

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import uz.turgunboyevjurabek.rizon.madels.UserMain.GetUsersMainResponse
import uz.turgunboyevjurabek.rizon.madels.auth.PostAuthRequest
import uz.turgunboyevjurabek.rizon.madels.auth.PostAuthResponse
import uz.turgunboyevjurabek.rizon.madels.coupon.GetUsersCoupon
import uz.turgunboyevjurabek.rizon.madels.coupon.transfer.GetUserForCouponTransferResponse
import uz.turgunboyevjurabek.rizon.madels.coupon.transfer.PostCouponTransferRequest
import uz.turgunboyevjurabek.rizon.madels.coupon.transfer.PostTransferResponse
import uz.turgunboyevjurabek.rizon.madels.filial.Filial
import uz.turgunboyevjurabek.rizon.madels.notification.GetNotificationResponse
import uz.turgunboyevjurabek.rizon.madels.promotion.GetPromotionResponse
import uz.turgunboyevjurabek.rizon.madels.salary.GetSalaryResponce
import uz.turgunboyevjurabek.rizon.madels.userOrders.GetUsersOrdersResponse
import uz.turgunboyevjurabek.rizon.madels.usersProducts.GetUserProductsResponse
import uz.turgunboyevjurabek.rizon.madels.usersProducts.post.PostProductsOrder
import uz.turgunboyevjurabek.rizon.madels.usersProfile.GetUserProfileResponse
import uz.turgunboyevjurabek.rizon.madels.usersProfile.userChangeInfo.PatchUserChangeInfoRequest
import uz.turgunboyevjurabek.rizon.madels.usersProfile.userChangeInfo.PatchUserChangeInfoResponse

interface ApiService {

    //    token olish authorization
    @POST("token/")
    suspend fun getToken(@Body postAuthRequest: PostAuthRequest): PostAuthResponse


    //    @Headers({"Authorization", "Bearer $token"})
    //mahsulotlar ro'yhatini olish
    @GET("users-products/")
    suspend fun getUsersProducts(@Header("Authorization") token: String): GetUserProductsResponse

    //filiallar ro'yhatini olish
    @GET("warehouses")
    suspend fun getFilials(@Header("Authorization") token: String):ArrayList<Filial>

    //buyurtma berish
    @POST("users/product/order/")
    suspend fun postProductOrder(
        @Header("Authorization") token: String,
        @Body postProductsOrderRequest: PostProductsOrder
    ): PostProductsOrder

    //buyurtmani bekor qilish
    @DELETE("users/product/order/{id}/")
    suspend fun deleteOrder(
        @Header("Authorization") token: String,
        @Path("id") id:String
    )

    //buyurtmalar ro'yhatini olish
    @GET("users-orders/")
    suspend fun getUsersOrders(@Header("Authorization") token: String): GetUsersOrdersResponse

    //profil uchun ma'lumotlar, shajara olib kelish
    @GET("users-profil/month/{month}")
    suspend fun getUsersProfile(
        @Header("Authorization") token: String,
        @Path("month") month: String
    ): GetUserProfileResponse

    //user ma'lumotlarini o'zgartirish
    @PATCH("change-user-info/")
    suspend fun changeUserInfo(@Header("Authorization") token:String, @Body patchUserChangeInfoRequest: PatchUserChangeInfoRequest) : PatchUserChangeInfoResponse

    //olingan oyliklar
    @GET("users-salary/payments/")
    suspend fun getSalaryPayments(@Header("Authorization") token: String): GetSalaryResponce

    //bildirishnomalar
    @GET("users-notifications")
    suspend fun getNotifications(@Header("Authorization") token: String): GetNotificationResponse

    //kuponlarni olib kelish
    @GET("users-coupons")
    suspend fun getUsersCoupon(@Header("Authorization") token: String): GetUsersCoupon

    //koupon transfer uchun ism familiya bilish
    @GET("users/{id}/user_id")
    suspend fun getNameForTransferCoupon(@Header("Authorization") token: String, @Path("id") id:String):GetUserForCouponTransferResponse
    //transfer coupon
    @POST("coupons/transfers/")
    suspend fun postTransferCoupon(@Header("Authorization") token: String, @Body postCouponTransferRequest: PostCouponTransferRequest): PostTransferResponse

    //asosiy main oyansidagi diagrammalarni, chegirmalarani chiqarish
    @GET("users-main-a")
    suspend fun getUsersMain(@Header("Authorization") token: String): GetUsersMainResponse

    //promotion maxsulotlarni olib kelish
    @GET("users-promotions")
    suspend fun getPromotions(@Header("Authorization") token: String): GetPromotionResponse

}
