package bg.dr.chillywe.user.service

import bg.dr.chillywe.user.UserNotFoundException
import bg.dr.chillywe.user.db.model.UserEntity
import bg.dr.chillywe.user.db.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

interface UserService {
    fun getAllUsers(): List<UserEntity>
    fun getUserById(userId: Long): UserEntity
    fun createUser(user: UserEntity): UserEntity
    fun updateUserById(userId: Long, user: UserEntity): UserEntity
    fun deleteUserById(userId: Long)
}

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    override fun getAllUsers(): List<UserEntity> = userRepository.findAll().toMutableList()

    override fun getUserById(userId: Long): UserEntity = userRepository.findById(userId)
        .orElseThrow { UserNotFoundException(HttpStatus.NOT_FOUND, "No matching user was found") }

    override fun createUser(user: UserEntity): UserEntity = userRepository.save(user)

    override fun updateUserById(userId: Long, user: UserEntity): UserEntity {
        return if (userRepository.existsById(userId)) {
            userRepository.save(
                UserEntity(
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