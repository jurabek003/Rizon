package uz.turgunboyevjurabek.rizon.madels.userOrders

data class GetUsersOrdersResponse(
    val active_orders: Int,
    val follower_link: String,
    val orders: List<Order>,
    val sale_link: String,
    val sales_history: List<SalesHistory>,
    val total_orders: Int
)