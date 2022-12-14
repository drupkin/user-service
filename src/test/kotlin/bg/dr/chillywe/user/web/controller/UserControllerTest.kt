package bg.dr.chillywe.user.web.controller

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class UserControllerTest {
    @Nested
    inner class GetUser {
        @Test
        fun `all fields are included`() {}
        @Test
        fun `limit parameter`() {}
        @Test
        fun `filter parameter`() {}
    }
    @Nested
    inner class DeleteUser {
        @Test
        fun `user is removed from db`() {}
        @Test
        fun `return 404 on invalid id parameter`() {}
        @Test
        fun `return 401 if not authorized`() {}
    }

}