package com.labs.security.config

import com.labs.security.security.CustomBasicAuthenticationEntryPoint
import com.labs.security.security.DbUserDetailsPasswordService
import com.labs.security.security.DbUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
  private val dbUserDetailsService: DbUserDetailsService,
  private val dbUserDetailsPasswordService: DbUserDetailsPasswordService
): WebSecurityConfigurerAdapter() {
  override fun configure(http: HttpSecurity) {
    http
      .csrf()
      .disable()
      .authorizeRequests { auth ->
        auth.antMatchers("/register", "/login")
          .permitAll()
          .anyRequest()
          .authenticated()
      }
      .httpBasic()
      .authenticationEntryPoint(authenticationEntryPoint())
      .and()
      .cors()
      .and()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
  }

  @Bean
  fun authProvider(): AuthenticationProvider {
    val daoAuthProvider = DaoAuthenticationProvider()
    daoAuthProvider.setPasswordEncoder(passwordEncoder())
    daoAuthProvider.setUserDetailsService(dbUserDetailsService)
    daoAuthProvider.setUserDetailsPasswordService(dbUserDetailsPasswordService)
    return daoAuthProvider
  }

  @Bean
  fun authenticationEntryPoint() = CustomBasicAuthenticationEntryPoint()

  @Bean
  fun passwordEncoder(): PasswordEncoder = Argon2PasswordEncoder()
}
