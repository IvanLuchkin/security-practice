package com.labs.security.repository

import com.labs.security.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Long> {
  fun getByUsername(username: String): User?
}
