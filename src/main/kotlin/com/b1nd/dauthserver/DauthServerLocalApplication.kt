package com.b1nd.dauthserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DauthServerLocalApplication

fun main(args: Array<String>) {
    runApplication<DauthServerLocalApplication>(*args)
}
