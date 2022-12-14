package bg.dr.chillywe.user.db.model

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "user")
class User(
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