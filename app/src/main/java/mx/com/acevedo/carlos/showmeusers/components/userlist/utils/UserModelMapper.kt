package mx.com.acevedo.carlos.showmeusers.components.userlist.utils

import androidx.annotation.StringRes
import mx.com.acevedo.carlos.showmeusers.R
import mx.com.acevedo.carlos.showmeusers.components.userlist.models.UserDbModel
import mx.com.acevedo.carlos.showmeusers.components.userlist.models.UserModel
import mx.com.acevedo.carlos.showmeusers.components.userlist.models.UserModelResponse
import mx.com.acevedo.carlos.showmeusers.utils.ResourceProvider
import mx.com.acevedo.carlos.showmeusers.utils.isNotNullOrBlank
import javax.inject.Inject

class UserModelMapper @Inject constructor(
    private val resourceProvider: ResourceProvider
) {

    /**
     * Maps [UserModelResponse] object to [UserModel] object, null values are converted to
     * empty strings
     * @see UserModelResponse
     */
    fun map(model: UserModelResponse): UserModel {
        val user = model.results?.first()
        val userName = user?.name
        val country = user?.location?.country.orEmpty()
        val smallProfilePicture = user?.profilePicture?.pictureMedium.orEmpty()
        val largeProfilePicture = user?.profilePicture?.pictureLarge.orEmpty()
        val streetName = user?.location?.street?.name.orEmpty()
        val streetNumber = user?.location?.street?.number.toString()
        val city = user?.location?.city.orEmpty()
        val state = user?.location?.state.orEmpty()
        val stringPostCode = when (val postCode = user?.location?.postCode) {
            is Double -> postCode.toInt().toString()
            is String -> postCode.toString()
            else -> ""
        }

        return UserModel(
            name = concatName(userName),
            nationality = country,
            profilePictureSmall = smallProfilePicture,
            profilePictureLarge = largeProfilePicture,
            postCode = stringPostCode,
            streetName = streetName,
            streetNumber = streetNumber,
            city = city,
            state = state
        )
    }

    /**
     * Builds details user model list to be showed to user
     * @param model Model which contains detailed user information
     */
    fun getUserDetailList(model: UserModel): List<Pair<String, String>> = with(model) {
        listOf(
            buildUserDetailItemModel(R.string.nationality_title, nationality),
            buildUserDetailItemModel(R.string.postcode_title, postCode),
            buildUserDetailItemModel(R.string.street_name_title, streetName),
            buildUserDetailItemModel(R.string.street_number_title, streetNumber),
            buildUserDetailItemModel(R.string.city_title, city),
            buildUserDetailItemModel(R.string.state, state)
        )
    }

    /**
     * Build user detail item model
     * @param title title string resource
     * @param field content of this item model
     */
    private fun buildUserDetailItemModel(
        @StringRes title: Int,
        field: String
    ): Pair<String, String> =
        Pair(resourceProvider.getString(title), field)

    /**
     * Maps [UserModel] list to [UserDbModel] list
     * @param userDbModelList db user list
     */
    fun mapToUserModelList(userDbModelList: List<UserDbModel>) =
        userDbModelList.map { userDbModel ->
            userDbModel.run {
                UserModel(
                    name = name,
                    nationality = nationality,
                    profilePictureSmall = profilePictureSmall,
                    profilePictureLarge = profilePictureLarge,
                    postCode = postCode,
                    streetName = streetName,
                    streetNumber = streetNumber,
                    city = city,
                    state = state
                )
            }
        }

    /**
     * Maps [UserDbModel] list to [UserModel] list
     * @param userModelList user model list
     */
    fun mapToUserDbModelList(userModelList: List<UserModel>) =
        userModelList.map { userModel ->
            userModel.run {
                UserDbModel(
                    name = name,
                    nationality = nationality,
                    profilePictureSmall = profilePictureSmall,
                    profilePictureLarge = profilePictureLarge,
                    postCode = postCode,
                    streetName = streetName,
                    streetNumber = streetNumber,
                    city = city,
                    state = state
                )
            }
        }

    /**
     * Concatenates user full name with format
     * @param userName [UserModelResponse.Name] object obtained from service API response
     */
    private fun concatName(userName: UserModelResponse.Name?): String {
        return formatStringValue(
            formatStringValue(userName?.title, userName?.first)
                    + formatStringValue(userName?.first, userName?.last)
                    + userName?.last.orEmpty()
        )
    }

    /**
     * Adds spaces if required or empty if value is null or blank
     * @param firstValue First string value to be showed
     * @param secondValue Second value placed next to [firstValue]
     */
    private fun formatStringValue(firstValue: String?, secondValue: String? = "") =
        when {
            firstValue.isNotNullOrBlank() && secondValue.isNotNullOrBlank() -> "$firstValue "
            firstValue.isNotNullOrBlank() -> firstValue.orEmpty()
            else -> ""
        }
}