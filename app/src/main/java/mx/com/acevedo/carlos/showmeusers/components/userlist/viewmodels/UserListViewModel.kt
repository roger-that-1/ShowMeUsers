package mx.com.acevedo.carlos.showmeusers.components.userlist.viewmodels

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import mx.com.acevedo.carlos.showmeusers.base.BaseViewModel
import mx.com.acevedo.carlos.showmeusers.components.userlist.models.UserModel
import mx.com.acevedo.carlos.showmeusers.components.userlist.repository.UserRepository
import mx.com.acevedo.carlos.showmeusers.utils.toLiveData
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val userModelList = MutableLiveData<List<UserModel>>()
    private val showSwipeLoading = MutableLiveData<Boolean>()

    init {
        getUserModelList()
    }

    /**
     * Provides Live data instance to View
     */
    fun getUserModelListObserver() = userModelList.toLiveData()

    /**
     * Provides swipe to refresh loading visibility
     */
    fun showSwipeLoading() = showSwipeLoading.toLiveData()

    /**
     * Gets new [UserModel] list from repository
     */
    fun updateUserList() = disposable.add(
        userRepository.updateUserList()
            .doOnSubscribe { showSwipeLoading.value = true }
            .doFinally { showSwipeLoading.value = false }
            .subscribe(userModelList::setValue) {
                showError.value = it.localizedMessage
            }
    )

    /**
     * It gets an [UserModel] list from repository, this method should not
     * know where the data came from
     */
    private fun getUserModelList() = disposable.add(
        userRepository.getUserList()
            .doOnSubscribe { showSwipeLoading.value = true }
            .doFinally { showSwipeLoading.value = false }
            .subscribe(userModelList::setValue) {
                showError.value = it.localizedMessage
            }
    )
}