package mx.com.acevedo.carlos.showmeusers.components.userlist.repository

import io.reactivex.rxjava3.core.Single
import mx.com.acevedo.carlos.showmeusers.components.userlist.api.UserServiceRepository
import mx.com.acevedo.carlos.showmeusers.components.userlist.models.UserModel
import mx.com.acevedo.carlos.showmeusers.components.userlist.utils.UserModelMapper
import mx.com.acevedo.carlos.showmeusers.utils.applySchedulers
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userServiceRepository: UserServiceRepository
) {
    /**
     * Gets a single user from repository
     */
    private fun getUser() = userServiceRepository.getUser()

    /**
     * Provides a 10 user list from repository, values received are mapper to [UserModel]
     * finally mutable list is parsed to immutable list to prevent observer modifications
     */
    fun getUserList(): Single<List<UserModel>> =
        getUser()
            .repeat(5)
            .map { UserModelMapper.map(it) }
            .toList()
            .map { it.toList() }
            .applySchedulers()
}