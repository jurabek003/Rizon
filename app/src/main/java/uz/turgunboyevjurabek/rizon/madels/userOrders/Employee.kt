package uz.turgunboyevjurabek.rizon.madels.userOrders

data class Employee(
    val date: String,
    val id: String,
    val user: UserX,
    val warehouse: WarehouseX
)