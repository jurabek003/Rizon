package uz.turgunboyevjurabek.rizon.madels.promotion

data class ProductsInterval(
    val amount: Int,
    val coupon: Int,
    val id: String,
    val name: String,
    val pause: Boolean,
    val photo: Any
)