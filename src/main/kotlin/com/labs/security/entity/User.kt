package com.labs.security.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails

@Table("user")
data class User(
  @Id
  val id: Long? = null,
  val username: String,
  val address: Address? = null,
  val password: String,
) {
  fun toApi(): ApiUser = ApiUser(username, address?.toApi())

  fun toUserDetails(): UserDetails = User.withUsername(username).password(password).authorities(emptyList()).build()
}
