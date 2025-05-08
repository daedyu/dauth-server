package com.b1nd.dauthserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class DauthServerLocalApplication

fun main(args: Array<String>) {
    runApplication<DauthServerLocalApplication>(*args)
}
