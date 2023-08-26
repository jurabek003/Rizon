package uz.turgunboyevjurabek.rizon.madels.coupon.transfer

data class GetUserForCouponTransferResponse(
    val address: String,
    val date: String,
    val dateOfBirth: String,
    val email: Any,
    val first_name: String,
    val id: String,
    val last_name: String,
    val passport: String,
    val phoneNumTwo: String,
    val phone_number: String,
    val photo: Any,
    val user_id: Long
)