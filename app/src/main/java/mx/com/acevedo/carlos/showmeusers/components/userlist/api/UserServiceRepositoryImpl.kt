package mx.com.acevedo.carlos.showmeusers.components.userlist.api

import io.reactivex.Single
import mx.com.acevedo.carlos.showmeusers.components.userlist.models.UserModel
import mx.com.acevedo.carlos.showmeusers.components.userlist.utils.UserModelMapper
import mx.com.acevedo.carlos.showmeusers.utils.applySchedulers
import javax.inject.Inject

class UserServiceRepositoryImpl @Inject constructor(
    private val userServiceHelper: UsersApiService,
    private val userModelMapper: UserModelMapper
) : UserServiceRepository {

    /**
     * Provides a [REPEAT_TIMES] users list from repository, values received are mapped to [UserModel],
     * finally mutable list is parsed to immutable list to prevent modifications at observer
     */
    override fun getUserModelList(): Single<List<UserModel>> {
        return userServiceHelper.getUserModel()
            .repeat(REPEAT_TIMES.toLong())
            .map { userModelMapper.map(it) }
            .toList()
            .map { it.toList() }
            .applySchedulers()
    }

    private companion object {
        const val REPEAT_TIMES = 5
    }
}