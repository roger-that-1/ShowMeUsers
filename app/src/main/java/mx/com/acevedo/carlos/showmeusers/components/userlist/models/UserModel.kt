package mx.com.acevedo.carlos.showmeusers.components.userlist.models

/**
 * User model class
 * It contains all user profile information
 */
data class UserModel(
    val profilePicture: String? = "",
    val name: String? = "",
    val nationality: String? = "",
    val additionalInfo: String? = ""
)