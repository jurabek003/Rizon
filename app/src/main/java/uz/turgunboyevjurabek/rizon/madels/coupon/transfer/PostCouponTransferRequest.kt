package uz.turgunboyevjurabek.rizon.madels.coupon.transfer

data class PostCouponTransferRequest(
    val comment: String,
    val `receiver`: String,
    val used: Int
)