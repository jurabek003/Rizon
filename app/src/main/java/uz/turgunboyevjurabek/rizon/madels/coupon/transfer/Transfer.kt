package uz.turgunboyevjurabek.rizon.madels.coupon.transfer

data class Transfer(
    val comment: String,
    val `receiver`: String,
    val used: Int
)