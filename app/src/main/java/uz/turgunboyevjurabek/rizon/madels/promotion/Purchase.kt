package uz.turgunboyevjurabek.rizon.madels.promotion

data class Purchase(
    val amount: Int,
    val coupon: Int,
    val created_time: String,
    val dateTime: String,
    val id: String,
    val promotion: Promotion,
    val updated_time: String,
    val user: User
)