package mx.com.acevedo.carlos.showmeusers.components.userlist.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.com.acevedo.carlos.showmeusers.components.userlist.models.UserModel
import mx.com.acevedo.carlos.showmeusers.utils.toLiveData
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor() : ViewModel() {

    // TODO Remove mock values
    private val userModelList = MutableLiveData(
        listOf(
            UserModel(
                "https://randomuser.me/api/portraits/med/men/22.jpg",
                "Lorem Ipsum",
                "Lorem Ipsum",
                ""
            ),
            UserModel(
                "https://randomuser.me/api/portraits/med/men/22.jpg",
                "Lorem Ipsum",
                "Lorem Ipsum",
                ""
            ),
            UserModel(
                "https://randomuser.me/api/portraits/med/men/22.jpg",
                "Lorem Ipsum",
                "Lorem Ipsum",
                ""
            ),
            UserModel(
                "https://randomuser.me/api/portraits/med/men/22.jpg",
                "Lorem Ipsum",
                "Lorem Ipsum",
                ""
            ),
        )
    )

    // TODO Remove async update
    init {
        viewModelScope.launch {
            delay(5000)
            userModelList.value = listOf(UserModel("", "TEST", "TEST", ""))
        }
    }

    /**
     * Provides Live data instance to View
     */
    fun getUserModelList() = userModelList.toLiveData()
}