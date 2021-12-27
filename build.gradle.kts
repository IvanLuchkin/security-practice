import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val springCloudVersion = "2020.0.4"

plugins {
  val kotlinVersion = "1.6.0"

  kotlin("jvm") version kotlinVersion
  kotlin("plugin.spring") version kotlinVersion

  id("org.springframework.boot") version "2.5.5"
  id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

group = "me.ivan"

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(16))
  }
}

tasks {
  withType<KotlinCompile> {
    kotlinOptions {
      freeCompilerArgs = listOf("-Xjsr305=strict")
      jvmTarget = "16"
    }
  }
}

repositories {
  mavenCentral()
}

dependencies {
  implementation(platform("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion"))
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
  implementation("org.springframework.boot:spring-boot-starter-validation")
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("org.springframework.retry:spring-retry")

  implementation("org.bouncycastle:bcprov-jdk15on:1.69")
  implementation("io.github.microutils:kotlin-logging:2.0.11")

  implementation("com.github.ben-manes.caffeine:caffeine:3.0.4")


  implementation("org.postgresql:postgresql")
  implementation("org.flywaydb:flyway-core")
  implementation("org.jolokia:jolokia-core")

  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

  implementation(kotlin("reflect"))
  implementation(kotlin("stdlib-jdk8"))
  implementation("org.springframework.boot:spring-boot-configuration-processor")
}
