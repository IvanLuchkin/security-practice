package com.labs.security.config

import com.labs.security.jdbc.AddressToEncryptedString
import com.labs.security.jdbc.EncryptedStringToAddress
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration

@Configuration
class JdbcConfiguration(
  private val addressToEncryptedStringConverter: AddressToEncryptedString,
  private val encryptedStringToAddressConverter: EncryptedStringToAddress
) : AbstractJdbcConfiguration() {

  @Bean
  override fun jdbcCustomConversions(): JdbcCustomConversions {
    return JdbcCustomConversions(
      listOf(
        addressToEncryptedStringConverter,
        encryptedStringToAddressConverter
      )
    )
  }
}
