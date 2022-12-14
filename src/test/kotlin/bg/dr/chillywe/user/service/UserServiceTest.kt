package bg.dr.chillywe.user.service

import bg.dr.chillywe.user.UserNotFoundException
import bg.dr.chillywe.user.UserTestData
import bg.dr.chillywe.user.db.model.User
import bg.dr.chillywe.user.db.repository.UserRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.http.HttpStatus
import java.time.LocalDate
import java.util.Optional

class UserServiceTest {
    private val userRepository: UserRepository = mockk(relaxed = true)
    private val userService = UserServiceImpl(userRepository)

    @BeforeEach
    fun init() {
        clearAllMocks()
    }

    @Test
    fun `should call its data source to retrieve users`() {
        // where
        userService.getAllUsers()
        // then
        verify(exactly = 1) { userRepository.findAll() }
    }

    @Test
    fun `should call its data source to retrieve user by id`() {
        // where
        val user = UserTestData.user
        // when
        every { userRepository.findById(any()) } returns user
        val expectedUser = UserTestData.expectedUser

        val result = userService.getUserById(1L)
        // then
        verify(exactly = 1) { userRepository.findById(any()) }

        result shouldBe expectedUser
    }

    @Test
    fun `should call its data source to retrieve user by id bud not user was found`() {
        // when
        every { userRepository.findById(any()) } returns Optional.empty()

        val exception = shouldThrow<UserNotFoundException> {
            userService.getUserById(1L)
        }
        // then
        verify(exactly = 1) { userRepository.findById(any()) }

        exception.statusCode shouldBe HttpStatus.NOT_FOUND
        exception.reason shouldBe "No matching user was found"
    }

    @Test
    fun `should call its data source to save new user`() {
        // where
        val user = UserTestData.user.get()
        // when
        every { userRepository.save(any()) } returns user
        val expectedUser = UserTestData.expectedUser

        val result = userService.createUser(user)
        // then
        verify(exactly = 1) { userRepository.save(any()) }

        result shouldBe expectedUser
    }

    @Test
    fun `should call its data source to update user by id`() {
        // where
        val user = UserTestData.user.get()
        // when
        every { userRepository.existsById(any()) } returns true
        every { userRepository.save(any()) } returns user
        val expectedUser = UserTestData.expectedUser

        val result = userService.updateUserById(1L, user)
        // then
        verify(exactly = 1) { userRepository.save(any()) }
        verify(exactly = 1) { userRepository.existsById(any()) }

        result shouldBe expectedUser
    }

    @Test
    fun `should call its data source to update user by id bud not user was found`() {
        // where
        val user = UserTestData.user.get()
        // when
        every { userRepository.existsById(any()) } returns false

        val exception = shouldThrow<UserNotFoundException> {
            userService.updateUserById(1L, user)
        }
        // then
        verify(exactly = 1) { userRepository.existsById(any()) }

        exception.statusCode shouldBe HttpStatus.NOT_FOUND
        exception.reason shouldBe "No matching user was found"
    }

    @Test
    fun `should call its data source to remove user by id`() {
        // when
        every { userRepository.existsById(any()) } returns true

        userService.deleteUserById(1L)
        // then
        verify(exactly = 1) { userRepository.deleteById(any()) }
        verify(exactly = 1) { userRepository.existsById(any()) }
    }

    @Test
    fun `should call its data source to remove user by id bud not user was found`() {
        // when
        every { userRepository.existsById(any()) } returns false

        val exception = shouldThrow<UserNotFoundException> {
            userService.deleteUserById(1L)
        }
        // then
        verify(exactly = 1) { userRepository.existsById(any()) }

        exception.statusCode shouldBe HttpStatus.NOT_FOUND
        exception.reason shouldBe "No matching user was found"
    }

}