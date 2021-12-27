package com.labs.security.entity

data class Address(
  val country: String
) {
  fun toApi(): ApiAddress = ApiAddress(country)
}
