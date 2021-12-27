package com.labs.security.jdbc

import com.labs.security.config.EncryptionProperties
import com.labs.security.entity.Address
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.stereotype.Component
import java.security.GeneralSecurityException
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

@Component
@ReadingConverter
class EncryptedStringToAddress(
  private val encryptionProperties: EncryptionProperties
): Converter<String, Address> {
  private val aes = "AES"
  private lateinit var secret: String
  private val key = SecretKeySpec(encryptionProperties.key.toByteArray(), aes)
  private val cipher = Cipher.getInstance(aes)

  override fun convert(source: String): Address {
    try {
      cipher.init(Cipher.DECRYPT_MODE, key)
      val decryptedAddress = cipher.doFinal(Base64.getDecoder().decode(source))
      return Address(decryptedAddress.toString())
    } catch (e: GeneralSecurityException) {
      throw IllegalStateException(e)
    }
  }
}
