package uz.turgunboyevjurabek.rizon.madels.userOrders

data class SalesHistory(
    val amount: Int,
    val created_time: String,
    val dateTime: String,
    val employee: Employee,
    val id: String,
    val product: Product,
    val summa: Int,
    val updated_time: String,
    val user: UserXX,
    val warehouse: WarehouseX
)