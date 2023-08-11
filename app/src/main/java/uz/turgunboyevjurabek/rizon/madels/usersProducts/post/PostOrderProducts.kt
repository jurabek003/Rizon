package uz.turgunboyevjurabek.rizon.madels.usersProducts.post

data class PostOrderProducts(
    val amount: Int,
    val done: Boolean,
    val product: ProductX,
    val user: String,
    val warehouse: Int
)