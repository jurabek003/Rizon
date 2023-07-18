package uz.turgunboyevjurabek.rizon.madels.usersProfile

data class GetUserProfileResponse(
    val follower_link: String,
    val sale_link: String,
    val user: User,
    val user_tree: UserTree
)