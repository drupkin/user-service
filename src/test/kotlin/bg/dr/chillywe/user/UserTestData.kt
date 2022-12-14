package bg.dr.chillywe.user

import bg.dr.chillywe.user.db.model.User
import java.time.LocalDate
import java.util.Optional

class UserTestData {

    companion object {
        val user = Optional.of(User(id = 1L, userName = "crazyJack", firstName = "Jack",
            lastName = "Harrison", email = "jack.harrison@leeds.united",
            dayOfBirth = LocalDate.of(1996, 11, 20)))

        val expectedUser = user.get()
    }

}