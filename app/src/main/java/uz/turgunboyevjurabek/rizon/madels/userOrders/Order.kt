package uz.turgunboyevjurabek.rizon.madels.userOrders

data class Order(
    val amount: Int,
    val date: String,
    val done: Boolean,
    val id: Int,
    val product: Product,
    val summa: Int,
    val user: Int,
    val warehouse: Int
)