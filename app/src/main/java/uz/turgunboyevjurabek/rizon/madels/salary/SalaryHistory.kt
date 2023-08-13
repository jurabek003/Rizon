package uz.turgunboyevjurabek.rizon.madels.salary

data class SalaryHistory(
    val bonus_for_followers_status: Int,
    val collective_bonus_amount: Int,
    val coupon: Int,
    val created_time: String,
    val debt: Int,
    val extra_bonus: Int,
    val forMentorship: Int,
    val for_followers_status_percent: Int,
    val id: String,
    val month: String,
    val paid: Int,
    val personal_bonus: Int,
    val salary: Int,
    val stat_percent: Int,
    val updated_time: String,
    val user: String,
    val user_score: Int,
    val user_status: String,
    val user_tree_score: Int,
    val user_tree_summa: Int,
    val warehouse: Warehouse
)