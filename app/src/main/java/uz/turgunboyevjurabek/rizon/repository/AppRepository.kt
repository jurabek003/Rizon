package uz.turgunboyevjurabek.rizon.repository

import uz.turgunboyevjurabek.rizon.madels.auth.PostAuthRequest
import uz.turgunboyevjurabek.rizon.madels.coupon.transfer.PostCouponTransferRequest
import uz.turgunboyevjurabek.rizon.madels.promotion.post.PostBuyPromotionRequest
import uz.turgunboyevjurabek.rizon.madels.usersProducts.post.PostProductsOrder
import uz.turgunboyevjurabek.rizon.madels.usersProfile.userChangeInfo.PatchUserChangeInfoRequest
import uz.turgunboyevjurabek.rizon.retrofit.ApiService

class AppRepository(val apiService: ApiService) {
    suspend fun getToken(postAuthRequest: PostAuthRequest) = apiService.getToken(postAuthRequest)
    suspend fun getUsersProducts(token:String) = apiService.getUsersProducts("Bearer $token")
    suspend fun getUsersOrders(token:String) = apiService.getUsersOrders("Bearer $token")

    suspend fun getAllFilials(token:String) = apiService.getFilials("Bearer $token")
    suspend fun postProductsOrder(token: String, postOrderProducts:PostProductsOrder) = apiService.postProductOrder("Bearer $token", postOrderProducts)
    suspend fun deleteProductsOrder(token: String, id:String) = apiService.deleteOrder("Bearer $token", id)

    suspend fun getUsersProfile(token:String, month:String) = apiService.getUsersProfile("Bearer $token", month)
    suspend fun changeUserInfo(token:String, patchUserChangeInfoRequest: PatchUserChangeInfoRequest) = apiService.changeUserInfo("Bearer $token", patchUserChangeInfoRequest)
    suspend fun getSalaryPayments(token: String) = apiService.getSalaryPayments("Bearer $token")
    suspend fun getNotifications(token: String) = apiService.getNotifications("Bearer $token")
    suspend fun getUserCoupon(token: String) = apiService.getUsersCoupon("Bearer $token")
    suspend fun getUserNameForTransferCoupon(token: String, id:String) = apiService.getNameForTransferCoupon("Bearer $token", id)
    suspend fun postTransferCoupons(token: String, postCouponTransferRequest: PostCouponTransferRequest) = apiService.postTransferCoupon("Bearer $token", postCouponTransferRequest)
    suspend fun getUserMain(token: String) = apiService.getUsersMain("Bearer $token")
    suspend fun getPromotions(token: String) = apiService.getPromotions("Bearer $token")
    suspend fun getPromotionBuyHistory(token: String) = apiService.getPromotionsBuyHistory("Bearer $token")
    suspend fun postBuyPromotion(token: String, postBuyPromotionRequest: PostBuyPromotionRequest) = apiService.postBuyPromotion("Bearer $token", postBuyPromotionRequest)
}