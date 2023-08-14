package uz.turgunboyevjurabek.rizon.madels.auth

data class PostAuthResponse(
    val access: String,
    val auth_status: String,
    val full_name: String,
    val refresh_token: String,
    val user_role: String
)