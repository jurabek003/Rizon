package uz.turgunboyevjurabek.rizon.madels.userOrders

data class Employee(
    val address: String,
    val date: String,
    val dateOfBirth: String,
    val first_name: String,
    val id: Int,
    val last_name: String,
    val passport: String,
    val password: String,
    val phoneNum: String,
    val phoneNumTwo: String,
    val user_id: Int,
    val username: String,
    val warehouse: Warehouse
)