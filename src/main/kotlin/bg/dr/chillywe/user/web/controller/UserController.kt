package bg.dr.chillywe.user.web.controller

import bg.dr.chillywe.user.db.model.User
import bg.dr.chillywe.user.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
class UserController(private val userService: UserService) {

    @GetMapping("/users")
    fun getAllUsers(): List<User> = userService.getAllUsers()

    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable("id") userId: Long): User = userService.getUserById(userId)

    @PostMapping("/users")
    fun createUser(@RequestBody payload: User): User = userService.createUser(payload)

    @PutMapping("/users/{id}")
    fun updateUserById(@PathVariable("id") userId: Long, @RequestBody payload: User): User =
        userService.updateUserById(userId, payload)

    @DeleteMapping("/users/{id}")
    fun deleteUserById(@PathVariable("id") userId: Long): Unit = userService.deleteUserById(userId)

}