package com.labs.security.api

import com.labs.security.entity.ApiRegistrationRequest
import com.labs.security.entity.ApiUser
import com.labs.security.repository.UserRepository
import com.labs.security.security.AuthenticationFacade
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@Validated
@RequestMapping
class AuthApi(
  private val userRepository: UserRepository,
  private val passwordEncoder: PasswordEncoder,
  private val authenticationFacade: AuthenticationFacade
) {

  @PostMapping("/register")
  fun register(@RequestBody req: ApiRegistrationRequest): ResponseEntity<ApiUser> {
    val user = req.toModel(passwordEncoder)
    val existingUser = userRepository.getByUsername(user.username)
    if (existingUser != null) {
      throw UserAlreadyExistsException("User ${existingUser.username} already exists.")
    }
    return ResponseEntity(userRepository.save(user).toApi(), HttpStatus.valueOf(201))
  }

  @GetMapping("/login")
  fun login(): ResponseEntity<ApiUser> {
    return ResponseEntity(authenticationFacade.getLoggedInUser().toApi(), HttpStatus.valueOf(200))
  }
}
