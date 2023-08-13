package uz.turgunboyevjurabek.rizon.madels.userOrders

data class Order(
    val amount: Int,
    val created_time: String,
    val date: String,
    val done: Boolean,
    val id: String,
    val product: Product,
    val summa: Int,
    val updated_time: String,
    val user: User,
    val warehouse: String
)