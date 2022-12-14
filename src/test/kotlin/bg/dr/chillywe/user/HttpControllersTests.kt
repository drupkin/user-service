package bg.dr.chillywe.user

import bg.dr.chillywe.user.service.UserService
import com.ninjasquad.springmockk.MockkBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc

@WebMvcTest
class HttpControllersTests(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var userService: UserService


//    @Test
//    fun `List users`() {
//        val juergen = User("springjuergen", "Juergen", "Hoeller")
//        val smaldini = User("smaldini", "Stéphane", "Maldini")
//        every { playerService.findAll() } returns listOf(juergen, smaldini)
//        mockMvc.perform(get("/api/user/").accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk)
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("\$.[0].login").value(juergen.login))
//            .andExpect(jsonPath("\$.[1].login").value(smaldini.login))
//    }
}