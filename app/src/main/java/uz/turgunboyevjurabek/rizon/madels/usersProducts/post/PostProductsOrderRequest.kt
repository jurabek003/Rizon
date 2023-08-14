package uz.turgunboyevjurabek.rizon.madels.usersProducts.post

data class PostProductsOrder(
    val amount: Int,
    val product: String,
    val warehouse: String
)