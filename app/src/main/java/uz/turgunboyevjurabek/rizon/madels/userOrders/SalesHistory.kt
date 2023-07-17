package uz.turgunboyevjurabek.rizon.madels.userOrders

data class SalesHistory(
    val amount: Int,
    val dateTime: String,
    val employee: Employee,
    val id: Int,
    val product: Product,
    val summa: Int,
    val user: User,
    val warehouse: Warehouse
)