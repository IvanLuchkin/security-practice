package com.labs.security.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("encryption")
class EncryptionProperties {
  lateinit var key: String
}
