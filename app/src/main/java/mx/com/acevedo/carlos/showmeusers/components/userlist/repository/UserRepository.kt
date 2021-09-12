package mx.com.acevedo.carlos.showmeusers.components.userlist.repository

import mx.com.acevedo.carlos.showmeusers.components.userlist.api.UserServiceRepository
import mx.com.acevedo.carlos.showmeusers.components.userlist.database.UserDbRepository
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userServiceRepository: UserServiceRepository,
    private val userDbRepository: UserDbRepository
) {

    /**
     * Gets users from database if database is not empty or fetch users from service when
     * database is empty
     */
    fun getUserList() =
        if (userDbRepository.isDbEmpty()) getUsersFromService() else getUsersFromDb()

    /**
     * Deletes database table and fetch new users from service
     */
    fun updateUserList() = userDbRepository.deleteTable().andThen(getUsersFromService())

    /**
     * Gets all users in database
     */
    private fun getUsersFromDb() = userDbRepository.getAll()

    /**
     * Fetch users from service and insert values to database
     */
    private fun getUsersFromService() =
        userServiceRepository
            .getUserModelList()
            .doOnSuccess {
                userDbRepository.insertAll(it).subscribe()
            }
}