package com.labs.security.api

import com.labs.security.entity.ApiAddress
import com.labs.security.repository.UserRepository
import com.labs.security.security.AuthenticationFacade
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@Validated
@RequestMapping
class UserDataApi(
  private val userRepository: UserRepository,
  private val authenticationFacade: AuthenticationFacade
) {

  @PostMapping("/address")
  fun addAddress(@RequestBody req: ApiAddress): ResponseEntity<ApiAddress> {
    val address = req.toModel()
    val user = authenticationFacade.getLoggedInUser()
    val userWithAddress = user.copy(address = address)
    val updatedUser = userRepository.save(userWithAddress)
    return ResponseEntity(updatedUser.address?.toApi(),HttpStatus.valueOf(200))
  }
}
