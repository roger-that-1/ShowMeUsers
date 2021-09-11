package mx.com.acevedo.carlos.showmeusers.components.userlist.api

import io.reactivex.rxjava3.core.Single
import mx.com.acevedo.carlos.showmeusers.components.userlist.models.UserModelResponse

interface UserServiceRepository {
    fun getUser(): Single<UserModelResponse>
}