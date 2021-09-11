package mx.com.acevedo.carlos.showmeusers.components.userlist.utils

import mx.com.acevedo.carlos.showmeusers.components.userlist.models.UserModel
import mx.com.acevedo.carlos.showmeusers.components.userlist.models.UserModelResponse

object UserModelMapper {

    /**
     * Maps [UserModelResponse] object to [UserModel] object
     * @see UserModelResponse
     */
    fun map(model: UserModelResponse): UserModel {
        val user = model.results?.first()
        val userName = user?.name
        val country = user?.location?.country
        val profilePicture = user?.profilePicture?.pictureMedium

        return UserModel(
            name = "${userName?.title} ${userName?.first} ${userName?.last}",
            nationality = country,
            profilePicture = profilePicture
        )
    }
}