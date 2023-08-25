package uz.turgunboyevjurabek.rizon.madels.UserMain

data class Discount(
    val about: String,
    val date: String,
    val discount: String,
    val expiration_date: String,
    val extraPrice: Int,
    val id: String,
    val manufacturer: String,
    val name: String,
    val photo_link: String,
    val price: Int,
    val product_type: String
)