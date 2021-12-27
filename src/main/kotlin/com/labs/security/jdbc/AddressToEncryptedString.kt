package com.labs.security.jdbc

import com.labs.security.config.EncryptionProperties
import com.labs.security.entity.Address
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter
import org.springframework.data.jdbc.core.convert.JdbcValue
import org.springframework.stereotype.Component
import java.security.GeneralSecurityException
import java.sql.JDBCType
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

@Component
@WritingConverter
class AddressToEncryptedString(
  private val encryptionProperties: EncryptionProperties
): Converter<Address, JdbcValue> {
  private val aes = "AES"
  private val key = SecretKeySpec(encryptionProperties.key.toByteArray(), aes)
  private val cipher = Cipher.getInstance(aes)

  override fun convert(source: Address): JdbcValue {
    try {
      cipher.init(Cipher.ENCRYPT_MODE, key)
      val encryptedAddress = Base64.getEncoder().encodeToString(cipher.doFinal(source.country.toByteArray()))
      return JdbcValue.of(encryptedAddress, JDBCType.VARCHAR)
    } catch (e: GeneralSecurityException) {
      throw IllegalStateException(e)
    }
  }
}
