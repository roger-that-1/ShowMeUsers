package mx.com.acevedo.carlos.showmeusers.components.userlist.api

import javax.inject.Inject

class UserServiceRepositoryImpl @Inject constructor(
    private val userServiceHelper: UsersApiService
) : UserServiceRepository {
    override fun getUser() = userServiceHelper.getUserModel()
}