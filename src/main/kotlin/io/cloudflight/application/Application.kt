package application

import io.cloudflight.ComponentMatrixCalculatorApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class Application {

    fun main(args: Array<String>){
//        SpringApplication.run(Application::class, args)
        runApplication<ComponentMatrixCalculatorApplication>(*args)

    }

}
