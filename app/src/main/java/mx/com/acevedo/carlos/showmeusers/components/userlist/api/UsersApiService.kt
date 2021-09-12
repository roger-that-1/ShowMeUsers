package mx.com.acevedo.carlos.showmeusers.components.userlist.api

import io.reactivex.Single
import mx.com.acevedo.carlos.showmeusers.components.userlist.models.UserModelResponse
import retrofit2.http.GET

interface UsersApiService {
    @GET("api")
    fun getUserModel(): Single<UserModelResponse>
}