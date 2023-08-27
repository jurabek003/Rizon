package uz.turgunboyevjurabek.rizon.madels.promotion

data class Promotion(
    val coupon: Int,
    val id: String,
    val name: String,
    val pause: Boolean,
    val photo: String
)