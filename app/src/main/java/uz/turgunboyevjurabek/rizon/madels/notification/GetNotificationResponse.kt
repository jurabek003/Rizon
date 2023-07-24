package uz.turgunboyevjurabek.rizon.madels.notification

data class GetNotificationResponse(
    val follower_link: String,
    val notifications: List<Notification>,
    val sale_link: String
)