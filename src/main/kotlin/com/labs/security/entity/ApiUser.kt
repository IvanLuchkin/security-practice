package com.labs.security.entity

import com.fasterxml.jackson.annotation.JsonProperty

data class ApiUser(
  @field:JsonProperty("username", required = true) val username: String,
  @field:JsonProperty("address") val address: ApiAddress? = null
)
