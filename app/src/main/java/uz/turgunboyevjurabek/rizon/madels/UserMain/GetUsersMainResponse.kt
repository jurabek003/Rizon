package uz.turgunboyevjurabek.rizon.madels.UserMain

data class GetUsersMainResponse(
    val ball: Int,
    val coupon: Int,
    val discounts: List<Discount>,
    val follower_link: String,
    val product_sales_data2: List<ProductSalesData2>,
    val salary_data: List<SalaryData>,
    val sale_link: String,
    val total_income: Int
)