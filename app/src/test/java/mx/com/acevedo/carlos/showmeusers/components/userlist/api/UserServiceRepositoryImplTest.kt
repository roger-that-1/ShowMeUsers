package mx.com.acevedo.carlos.showmeusers.components.userlist.api

import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import mx.com.acevedo.carlos.showmeusers.components.userlist.models.UserModelResponse
import mx.com.acevedo.carlos.showmeusers.components.userlist.utils.UserModelMapper
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserServiceRepositoryImplTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var usersApiService: UsersApiService

    @Mock
    private lateinit var userModelMapper: UserModelMapper

    private lateinit var userServiceRepositoryImpl: UserServiceRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        userServiceRepositoryImpl = UserServiceRepositoryImpl(
            usersApiService,
            userModelMapper
        )
    }

    @Test
    fun `Get user model list should call user api service get user model`() {

        whenever(usersApiService.getUserModel()).thenReturn(Single.just(UserModelResponse(null)))

        userServiceRepositoryImpl.getUserModelList()

        verify(usersApiService).getUserModel()
    }
}