package mx.com.acevedo.carlos.showmeusers.components.userlist.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.reset
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import mx.com.acevedo.carlos.showmeusers.components.userlist.api.RxImmediateSchedulerRule
import mx.com.acevedo.carlos.showmeusers.components.userlist.models.UserModel
import mx.com.acevedo.carlos.showmeusers.components.userlist.repository.UserRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserListViewModelTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var userModelListObserver: Observer<List<UserModel>>

    private lateinit var viewModel: UserListViewModel

    private val userModel = UserModel(
        "",
        "",
        "",
        "",
        "", "",
        "",
        "",
        ""
    )

    private val singleModelList = Single.just(listOf(userModel))

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        whenever(userRepository.getUserList()).thenReturn(
            singleModelList
        )

        viewModel = UserListViewModel(
            userRepository
        ).apply {
            getUserModelListObserver().observeForever(userModelListObserver)
        }
    }

    @Test
    fun `Creating view model should call get user list and show user model list`() {

        setUp()

        verify(userRepository).getUserList()
        verify(userModelListObserver).onChanged(listOf(userModel))
    }

    @Test
    fun `Update user list should call repository update user list and update user model list`() {

        reset(userModelListObserver)
        whenever(userRepository.updateUserList()).thenReturn(
            singleModelList
        )

        viewModel.updateUserList()

        verify(userRepository).updateUserList()
        verify(userModelListObserver).onChanged(listOf(userModel))
    }
}