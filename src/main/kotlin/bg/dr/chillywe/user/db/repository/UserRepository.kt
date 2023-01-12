package bg.dr.chillywe.user.db.repository

import bg.dr.chillywe.user.db.model.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserEntity, Long> {
}