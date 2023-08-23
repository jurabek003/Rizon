package uz.turgunboyevjurabek.rizon.madels.usersProfile.userChangeInfo

data class PatchUserChangeInfoRequest(
    var address: String,
    var dateOfBirth: String,
    var first_name: String,
    var last_name: String,
    var passport: String,
    var phone_number:String,
    var phoneNumTwo: String
)