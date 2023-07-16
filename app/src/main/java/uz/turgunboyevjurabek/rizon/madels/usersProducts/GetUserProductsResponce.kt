package uz.turgunboyevjurabek.rizon.madels.usersProducts

data class GetUserProductsResponse(
    val discounts: List<Discount>,
    val follower_link: String,
    val products: List<Product>,
    val sale_link: String
)