package uz.turgunboyevjurabek.rizon.madels.coupon

data class Transfer(
    val comment: String,
    val dateTime: String,
    val id: String,
    val `receiver`: Receiver,
    val sender: Sender,
    val used: Int
)