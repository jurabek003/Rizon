package uz.turgunboyevjurabek.rizon.madels.promotion.post

data class PostBuyPromotionResponse(
    val message: String,
    val purchase: Purchase,
    val success: Boolean
)