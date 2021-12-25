package com.labs.security.security

import com.labs.security.api.NotFoundException
import com.labs.security.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class DbUserDetailsService(
  private val userRepository: UserRepository,
): UserDetailsService {
  override fun loadUserByUsername(username: String): UserDetails {
    val user = userRepository.getByUsername(username)
      ?: throw NotFoundException("User $username doesn't exist.")
    return user.toUserDetails()
  }
}
