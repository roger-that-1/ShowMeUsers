package mx.com.acevedo.carlos.showmeusers.components.userlist.viewmodels

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import mx.com.acevedo.carlos.showmeusers.base.BaseViewModel
import mx.com.acevedo.carlos.showmeusers.components.userlist.models.UserModel
import mx.com.acevedo.carlos.showmeusers.components.userlist.utils.UserModelMapper

class UserDetailViewModel @AssistedInject constructor(
    @Assisted private val userModel: UserModel,
    private val userModelMapper: UserModelMapper
) : BaseViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(userModel: UserModel): UserDetailViewModel
    }

    /**
     * Provides user model to view
     */
    fun getUserModel() = userModel

    /**
     * Provides user details item list to be showed to user
     */
    fun getDetailItemModelList() = userModelMapper.getUserDetailList(userModel)
}