package uz.turgunboyevjurabek.rizon.madels.coupon

data class GetUsersCoupon(
    val follower_link: String,
    val sale_link: String,
    val transfers: List<Transfer>,
    val user: User
)