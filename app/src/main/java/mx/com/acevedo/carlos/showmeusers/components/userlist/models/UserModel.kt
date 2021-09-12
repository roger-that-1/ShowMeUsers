package mx.com.acevedo.carlos.showmeusers.components.userlist.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * User model class
 * It contains all user profile information
 */
@Parcelize
data class UserModel(
    val name: String,
    val nationality: String,
    val profilePictureLarge: String,
    val profilePictureSmall: String,
    val postCode: String,
    val streetNumber: String,
    val streetName: String,
    val city: String,
    val state: String
) : Parcelable