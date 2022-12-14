package bg.dr.chillywe.user.db.repository

import bg.dr.chillywe.user.db.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
}