package uz.turgunboyevjurabek.rizon.madels.coupon

data class Receiver(
    val address: String,
    val date: String,
    val dateOfBirth: String,
    val first_name: String,
    val id: Int,
    val last_name: String,
    val passport: String,
    val phoneNum: String,
    val phoneNumTwo: String,
    val photo: Any,
    val user_id: Int
)