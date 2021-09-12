package mx.com.acevedo.carlos.showmeusers.components.userlist.utils

import mx.com.acevedo.carlos.showmeusers.components.userlist.models.UserModelResponse
import mx.com.acevedo.carlos.showmeusers.utils.ResourceProvider
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UserModelMapperTest {

    @Mock
    private lateinit var resourceProvider: ResourceProvider

    private lateinit var userModelMapper: UserModelMapper

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        userModelMapper = UserModelMapper(
            resourceProvider
        )
    }

    @Test
    fun `Mapping user model response to user model, name should be parsed as espected`() {

        val userModelResponse = UserModelResponse(
            results = listOf(
                UserModelResponse.Result(
                    name = UserModelResponse.Name("Ing", "Carlos", "Acevedo"),
                    location = null,
                    profilePicture = null
                )
            )
        )

        val userModel = userModelMapper.map(userModelResponse)

        assertEquals(TEST_NAME, userModel.name)
    }

    @Test
    fun `Mapping user model response to user model, name should be empty if al name params are null`() {

        val userModelResponse = UserModelResponse(
            results = listOf(
                UserModelResponse.Result(
                    name = null,
                    location = null,
                    profilePicture = null
                )
            )
        )

        val userModel = userModelMapper.map(userModelResponse)

        assertEquals("", userModel.name)
    }

    @Test
    fun `Mapping user model response to user model, name should be parsed as expected when some name field is null or empty`() {

        val userModelResponse = UserModelResponse(
            results = listOf(
                UserModelResponse.Result(
                    name = UserModelResponse.Name(
                        title = null,
                        first = "Carlos",
                        last = "Acevedo"
                    ),
                    location = null,
                    profilePicture = null
                )
            )
        )

        val userModel = userModelMapper.map(userModelResponse)

        assertEquals(TEST_NAME_NO_TITLE, userModel.name)
    }

    private companion object {
        const val TEST_NAME = "Ing Carlos Acevedo"
        const val TEST_NAME_NO_TITLE = "Carlos Acevedo"
    }
}