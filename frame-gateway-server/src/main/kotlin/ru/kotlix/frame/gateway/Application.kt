package ru.kotlix.frame.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@SpringBootApplication
@EnableWebSecurity
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
