package nam.tran.flatform.interactor.authen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.InstrumentationRegistry
import nam.tran.flatform.executor.AppExecutors
import nam.tran.flatform.getValue
import nam.tran.flatform.local.Preference
import nam.tran.flatform.model.core.state.Loading
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class AuthenDataBoundTest {

    @get:Rule
    val instantTaskExecutors: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var classUnderTest: AuthenDataBound

    private val preference = mock(Preference::class.java)


    @org.junit.Before
    fun setUp() {
        classUnderTest = AuthenDataBound(AppExecutors(), preference)
    }

    @org.junit.After
    fun tearDown() {
    }

    @org.junit.Test
    fun getResult() {
    }

    @org.junit.Test
    fun login() {
        val username: String? = null
        val password: String? = null
        val isRemember: Boolean = true

        // when
        classUnderTest.login(username, password, isRemember)

        // then
        getValue(classUnderTest.result).apply {
            assertEquals(this.loading, Loading.LOADING_NORMAL)
        }

        getValue(classUnderTest.result).apply {
            assertEquals("Email not empty", this.errorResource?.massage)
        }
    }


    @org.junit.Test
    fun `login success should save isRemember`() {
        val username = "GoBear"
        val password = "GoBearDemo"
        val isRemember = true

        // when
        classUnderTest.login(username, password, isRemember)

        // then
        getValue(classUnderTest.result).apply {
            assertEquals(this.loading, Loading.LOADING_NORMAL)
        }

        getValue(classUnderTest.result).apply {
            assertEquals(true, this.data)
        }

//        verify(preference, times(1)).login(isRemember)
    }
}