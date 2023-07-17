package uz.turgunboyevjurabek.rizon.madels.userOrders

data class Product(
    val about: String,
    val date: String,
    val expiration_date: String,
    val extraPrice: Int,
    val id: Int,
    val manufacturer: String,
    val name: String,
    val photo_link: String,
    val price: Int,
    val product_type: String
)