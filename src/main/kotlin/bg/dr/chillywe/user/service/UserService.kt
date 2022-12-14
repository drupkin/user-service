package bg.dr.chillywe.user.service

import bg.dr.chillywe.user.UserNotFoundException
import bg.dr.chillywe.user.db.model.User
import bg.dr.chillywe.user.db.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

interface UserService {
    fun getAllUsers(): List<User>
    fun getUserById(userId: Long): User
    fun createUser(user: User): User
    fun updateUserById(userId: Long, user: User): User
    fun deleteUserById(userId: Long)
}

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    override fun getAllUsers(): List<User> = userRepository.findAll().toMutableList()

    override fun getUserById(userId: Long): User = userRepository.findById(userId)
        .orElseThrow { UserNotFoundException(HttpStatus.NOT_FOUND, "No matching user was found") }

    override fun createUser(user: User): User = userRepository.save(user)

    override fun updateUserById(userId: Long, user: User): User {
        return if (userRepository.existsById(userId)) {
            userRepository.save(
                User(
                    id = user.id,
                    firstName = user.firstName,
                    userName = user.userName,
                    lastName = user.lastName,
                    email = user.email,
                    dayOfBirth = user.dayOfBirth
                )
            )
        } else throw UserNotFoundException(HttpStatus.NOT_FOUND, "No matching user was found")
    }

    override fun deleteUserById(userId: Long) {
        return if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId)
        } else throw UserNotFoundException(HttpStatus.NOT_FOUND, "No matching user was found")
    }



}