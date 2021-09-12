package mx.com.acevedo.carlos.showmeusers.components.userlist.api

import io.reactivex.Single
import mx.com.acevedo.carlos.showmeusers.components.userlist.models.UserModel

interface UserServiceRepository {
    fun getUserModelList(): Single<List<UserModel>>
}