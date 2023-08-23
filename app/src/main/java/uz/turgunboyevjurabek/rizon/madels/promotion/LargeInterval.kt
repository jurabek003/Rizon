package uz.turgunboyevjurabek.rizon.madels.promotion

import java.io.Serializable

data class ProductsInterval(
    val amount: Int,
    val coupon: Int,
    val id: String,
    val name: String,
    val pause: Boolean,
    val photo: Any
):Serializable