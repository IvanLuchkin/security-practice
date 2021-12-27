package com.labs.security.entity

import com.fasterxml.jackson.annotation.JsonProperty

data class ApiAddress(
  @field:JsonProperty("country", required = true) val country: String
) {
  fun toModel(): Address = Address(country)
}
