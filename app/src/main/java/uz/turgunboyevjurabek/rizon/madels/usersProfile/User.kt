package uz.turgunboyevjurabek.rizon.madels.usersProfile

import java.util.Date

data class User(
    val bonus_for_followers_status: Int,
    val collective_bonus_amount: Int,
    val coupon: Double,
    val date: String,
    val debt: Double,
    val extra_bonus: Int,
    val first_name: String,
    val forMentorship: Int,
    val for_followers_status_percent: Int,
    val id: Int,
    val last_name: String,
    val paid: Int,
    val personal_bonus: Double,
    val phoneNum: String,
    val phoneNumTwo: String,
    val photo: Any,
    val salary: Double,
    val stat_percent: Int,
    val user_id: Int,
    val user_score: Double,
    val user_status: String,
    val user_tree_score: Double,
    val user_tree_summa: Int,
    val warehouse: Warehouse,

    val address: String,
    val passport: String,
    val dateOfBirth: String
)