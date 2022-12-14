package bg.dr.chillywe.user

import org.springframework.http.HttpStatus

class UserNotFoundException(val statusCode: HttpStatus, val reason: String) : Exception()