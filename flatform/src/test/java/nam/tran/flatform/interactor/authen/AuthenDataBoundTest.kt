package nam.tran.flatform.interactor.authen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import nam.tran.flatform.InstantAppExecutors
import nam.tran.flatform.local.IPreference
import nam.tran.flatform.local.Preference
import nam.tran.flatform.mock
import nam.tran.flatform.model.core.StatusCode
import nam.tran.flatform.model.core.state.ErrorResource
import nam.tran.flatform.model.core.state.Loading
import nam.tran.flatform.model.core.state.Resource
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class AuthenDataBoundTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var classUnderTest: AuthenDataBound

    private val iPreference = mock(IPreference::class.java)


    @org.junit.Before
    fun setUp() {
        classUnderTest = AuthenDataBound(InstantAppExecutors(), iPreference)
    }

    @org.junit.After
    fun tearDown() {
    }

    @org.junit.Test
    fun getResult() {
    }

    @org.junit.Test
    fun `login with empty user name`() {
        val username: String? = null
        val observer = mock<Observer<Resource<Boolean>>>()
        classUnderTest.result.observeForever(observer)

        // when
        classUnderTest.emptyUser(username)
        Mockito.verify(observer).onChanged(Resource.error(ErrorResource("User not empty", StatusCode.EMPTY_FIELD),false))
    }


    @org.junit.Test
    fun `login with empty password`() {
        val password = null

        val observer = mock<Observer<Resource<Boolean>>>()
        classUnderTest.result.observeForever(observer)

        // when
        classUnderTest.emptyPassword(password)
        Mockito.verify(observer).onChanged(Resource.error(ErrorResource("Password not empty", StatusCode.EMPTY_FIELD),false))
    }

    @org.junit.Test
    fun `login fail`() {
        val username = "abc"
        val password = "bcd"

        val observer = mock<Observer<Resource<Boolean>>>()
        classUnderTest.result.observeForever(observer)

        // when
        classUnderTest.loginFail(username,password)
        Mockito.verify(observer).onChanged(Resource.error(ErrorResource("Email or Password not correct",classUnderTest.INCORRECT),false))
    }

    @org.junit.Test
    fun `login success`() {
        val username = "GoBear"
        val password = "GoBearDemo"
        val isRemember = false

        val observer = mock<Observer<Resource<Boolean>>>()
        classUnderTest.result.observeForever(observer)

        // when
        classUnderTest.login(username,password,isRemember)
        Mockito.verify(observer).onChanged(Resource.loading(null))
        Mockito.verify(observer).onChanged(Resource.success(true))
        verify(iPreference).login(isRemember)
    }
}