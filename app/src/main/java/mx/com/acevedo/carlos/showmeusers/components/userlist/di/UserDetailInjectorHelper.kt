package mx.com.acevedo.carlos.showmeusers.components.userlist.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.com.acevedo.carlos.showmeusers.components.userlist.models.UserModel
import mx.com.acevedo.carlos.showmeusers.components.userlist.viewmodels.UserDetailViewModel

object UserDetailInjectorHelper {

    /**
     * Provides [UserDetailViewModel] factory instance required to create view model
     * @param assistedFactory provides dagger factory instance
     * @param userModel required for assisted injection
     */
    fun provideFactory(
        assistedFactory: UserDetailViewModel.Factory,
        userModel: UserModel
    ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return assistedFactory.create(userModel) as T
        }
    }
}