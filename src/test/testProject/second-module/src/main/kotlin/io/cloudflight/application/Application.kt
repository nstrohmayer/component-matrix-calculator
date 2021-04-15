package io.cloudflight.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
internal open class TestApplication

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}


