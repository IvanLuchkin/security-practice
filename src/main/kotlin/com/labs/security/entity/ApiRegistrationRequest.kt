package com.labs.security.entity

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.security.crypto.password.PasswordEncoder

data class ApiRegistrationRequest(
  @field:JsonProperty("username", required = true) val username: String,

  @field:JsonProperty("password", required = true) val password: String,
) {
  fun toModel(passwordEncoder: PasswordEncoder): User = User(
    username = username,
    password = passwordEncoder.encode(password)
  )
}
