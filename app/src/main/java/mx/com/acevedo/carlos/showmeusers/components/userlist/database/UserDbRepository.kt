package mx.com.acevedo.carlos.showmeusers.components.userlist.database

import mx.com.acevedo.carlos.showmeusers.components.userlist.models.UserDbModel
import mx.com.acevedo.carlos.showmeusers.components.userlist.models.UserModel
import mx.com.acevedo.carlos.showmeusers.components.userlist.utils.UserModelMapper
import mx.com.acevedo.carlos.showmeusers.utils.applySchedulers
import mx.com.acevedo.carlos.showmeusers.utils.applySchedulersOnIo
import javax.inject.Inject

class UserDbRepository @Inject constructor(
    private val userModelDatabase: UserModelDatabase,
    private val userModelMapper: UserModelMapper
) {

    /**
     * Inserts [UserDbModel] in database
     */
    fun insertAll(modelList: List<UserModel>) =
        userModelDatabase.userModelDao()
            .insertAll(userModelMapper.mapToUserDbModelList(modelList))
            .applySchedulers()

    /**
     * Gets all [UserDbModel] from database
     */
    fun getAll() = userModelDatabase.userModelDao().getAll().map { userDbModelList ->
        userModelMapper.mapToUserModelList(userDbModelList)
    }.applySchedulers()

    /**
     * It clears all database tables
     */
    fun deleteTable() = userModelDatabase.userModelDao().deleteAll().applySchedulersOnIo()

    /**
     * Checks if [UserModelDatabase] table is empty
     */
    fun isDbEmpty() = getUsersCount() == 0

    /**
     * Gets table size
     */
    private fun getUsersCount(): Int =
        userModelDatabase.userModelDao().getDataCount().applySchedulersOnIo().blockingGet()
}