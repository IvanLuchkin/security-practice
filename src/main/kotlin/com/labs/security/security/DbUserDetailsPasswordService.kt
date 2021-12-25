package com.labs.security.security

import com.labs.security.api.NotFoundException
import com.labs.security.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsPasswordService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DbUserDetailsPasswordService(
  private val userRepository: UserRepository,
): UserDetailsPasswordService {
  override fun updatePassword(userDetails: UserDetails, newPassword: String): UserDetails {
    val user = userRepository.getByUsername(userDetails.username)
      ?: throw NotFoundException("User ${userDetails.username} doesn't exist.")
    return user.copy(password = newPassword).toUserDetails()
  }
}
