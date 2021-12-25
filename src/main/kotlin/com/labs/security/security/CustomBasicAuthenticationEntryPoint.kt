package com.labs.security.security

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class CustomBasicAuthenticationEntryPoint : BasicAuthenticationEntryPoint() {
  override fun commence(request: HttpServletRequest, response: HttpServletResponse, authEx: AuthenticationException) {
    response.status = HttpServletResponse.SC_UNAUTHORIZED
    val writer = response.writer
    writer.println("HTTP Status 401 - " + authEx.message)
  }

  override fun afterPropertiesSet() {
    realmName = "YOUR REALM"
    super.afterPropertiesSet()
  }
}
