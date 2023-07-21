package uz.turgunboyevjurabek.rizon.madels.salary

data class SalaryHistory(
    val bonus_for_followers_status: Int,
    val collective_bonus_amount: Double,
    val coupon: Double,
    val date: String,
    val debt: Int,
    val extra_bonus: Int,
    val forMentorship: Int,
    val for_followers_status_percent: Int,
    val paid: Int,
    val paymentDate: String,
    val personal_bonus: Double,
    val salary: Double,
    val salary_payer: SalaryPayer,
    val stat_percent: Int,
    val user: User,
    val user_score: Double,
    val user_status: String,
    val user_tree_score: Double,
    val user_tree_summa: Int
)