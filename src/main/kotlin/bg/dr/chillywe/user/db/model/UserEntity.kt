package bg.dr.chillywe.user.db.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "user")
class UserEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column(name = "user_name", unique = true, nullable = false)
    val userName: String,
    @Column(name = "first_name", nullable = false)
    val firstName: String,
    @Column(name = "last_name", nullable = false)
    val lastName: String,
    @Column(name = "email_address", nullable = false)
    val email: String,
    @Column(name = "day_of_birth", nullable = false)
    val dayOfBirth: LocalDate
)