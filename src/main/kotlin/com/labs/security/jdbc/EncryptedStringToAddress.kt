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
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec

@Component
@ReadingConverter
class EncryptedStringToAddress(
  encryptionProperties: EncryptionProperties
): Converter<String, Address> {
  private val aes = "AES/GCM/NoPadding"
  private val key = SecretKeySpec(encryptionProperties.key.toByteArray(), "AES")
  private val cipher = Cipher.getInstance(aes)

  override fun convert(source: String): Address {
    try {
      val base64DecipheredSource = Base64.getDecoder().decode(source)
      val gcmIv = GCMParameterSpec(128, base64DecipheredSource, 0, 12)
      cipher.init(Cipher.DECRYPT_MODE, key, gcmIv)
      val decryptedAddress = cipher.doFinal(
        base64DecipheredSource,
        12,
        base64DecipheredSource.size - 12)
      return Address(decryptedAddress.toString())
    } catch (e: GeneralSecurityException) {
      throw IllegalStateException(e)
    }
  }
}
