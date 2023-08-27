package uz.turgunboyevjurabek.rizon.madels.promotion

data class GetPromotionBuyHistoryResponse(
    val coupon: Int,
    val follower_link: String,
    val purchases: List<Purchase>,
    val sale_link: String
)