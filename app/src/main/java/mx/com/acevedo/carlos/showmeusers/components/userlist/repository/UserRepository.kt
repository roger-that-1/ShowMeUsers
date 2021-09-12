package mx.com.acevedo.carlos.showmeusers.components.userlist.repository

import io.reactivex.rxjava3.core.Single
import mx.com.acevedo.carlos.showmeusers.components.userlist.api.UserServiceRepository
import mx.com.acevedo.carlos.showmeusers.components.userlist.models.UserModel
import mx.com.acevedo.carlos.showmeusers.components.userlist.utils.UserModelMapper
import mx.com.acevedo.carlos.showmeusers.utils.applySchedulers
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userServiceRepository: UserServiceRepository,
    private val userModelMapper: UserModelMapper
) {
    /**
     * Gets a single user from repository
     */
    private fun getUser() = userServiceRepository.getUser()

    /**
     * Provides a [REPEAT_TIMES] users list from repository, values received are mapped to [UserModel],
     * finally mutable list is parsed to immutable list to prevent modifications at observer
     */
    fun getUserList(): Single<List<UserModel>> =
        getUser()
            .repeat(REPEAT_TIMES.toLong())
            .map { userModelMapper.map(it) }
            .toList()
            .map { it.toList() }
            .applySchedulers()

    private companion object {
        const val REPEAT_TIMES = 5
    }
}