package bg.dr.chillywe.user.web.controller

import bg.dr.chillywe.user.db.model.UserEntity
import bg.dr.chillywe.user.messaging.UserKafkaService
import bg.dr.chillywe.user.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
class UserController(
    private val userService: UserService,
    private val userKafkaService: UserKafkaService
) {

    // TODO: for test produce kafka message
    @GetMapping("/send-created-message")
    fun testKafka() {
        userKafkaService.sendUserCreatedMessage()
    }

    @GetMapping("/users")
    fun getAllUsers(): List<UserEntity> = userService.getAllUsers()

    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable("id") userId: Long): UserEntity = userService.getUserById(userId)

    @PostMapping("/users")
    fun createUser(@RequestBody payload: UserEntity): UserEntity = userService.createUser(payload)

    @PutMapping("/users/{id}")
    fun updateUserById(@PathVariable("id") userId: Long, @RequestBody payload: UserEntity): UserEntity =
        userService.updateUserById(userId, payload)

    @DeleteMapping("/users/{id}")
    fun deleteUserById(@PathVariable("id") userId: Long): Unit = userService.deleteUserById(userId)

}