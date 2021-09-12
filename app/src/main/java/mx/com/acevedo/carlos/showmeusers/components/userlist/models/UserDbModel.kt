package mx.com.acevedo.carlos.showmeusers.components.userlist.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * [UserDbModel] data class contains the information that will be saved at Database
 */
@Entity(tableName = "userModel")
data class UserDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "profile_picture_large")
    val profilePictureLarge: String,
    @ColumnInfo(name = "profile_picture_small")
    val profilePictureSmall: String,
    @ColumnInfo(name = "nationality")
    val nationality: String,
    @ColumnInfo(name = "postcode")
    val postCode: String,
    @ColumnInfo(name = "street_number")
    val streetNumber: String,
    @ColumnInfo(name = "street_name")
    val streetName: String,
    @ColumnInfo(name = "city")
    val city: String,
    @ColumnInfo(name = "state")
    val state: String
)