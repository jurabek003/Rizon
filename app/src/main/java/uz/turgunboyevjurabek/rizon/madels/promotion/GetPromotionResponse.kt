package uz.turgunboyevjurabek.rizon.madels.promotion

data class GetPromotionResponse(
    val coupon: Int,
    val large_interval: List<ProductsInterval>,
    val middle_interval: List<ProductsInterval>,
    val pending_coupon: Int,
    val small_interval: List<ProductsInterval>
)