package com.labs.security.util

import java.security.SecureRandom

fun generateIv(): ByteArray {
  val iv = ByteArray(12)
  SecureRandom().nextBytes(iv)
  return iv
}
