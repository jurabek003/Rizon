package uz.turgunboyevjurabek.rizon.madels.userOrders

import uz.turgunboyevjurabek.rizon.madels.usersProfile.Warehouse

data class Employee(
    val date: String,
    val id: String,
    val user: UserX,
    val warehouse: Warehouse
)