package com.labs.security.security

import com.labs.security.api.NotFoundException
import com.labs.security.api.UnauthorizedException
import com.labs.security.entity.User
import com.labs.security.repository.UserRepository
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AuthenticationFacade(
  private val userRepository: UserRepository
) {
  private fun getAuthentication(): Authentication {
    return SecurityContextHolder.getContext().authentication ?: throw
    UnauthorizedException("Unauthorized")
  }

  fun getLoggedInUser(): User {
    val authentication = getAuthentication()
    println(authentication.toString())
    return userRepository.getByUsername(authentication.name) ?: throw
    NotFoundException("User not found")
  }
}
