package mx.com.acevedo.carlos.showmeusers.components.userlist.api

import io.reactivex.rxjava3.core.Single
import mx.com.acevedo.carlos.showmeusers.components.userlist.models.UserModelResponse
import retrofit2.http.GET

interface UsersApiService {
    @GET("api")
    fun getUserModel(): Single<UserModelResponse>
}