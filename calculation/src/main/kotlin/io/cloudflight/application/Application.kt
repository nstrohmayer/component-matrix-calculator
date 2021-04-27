package io.cloudflight.application

import io.cloudflight.converter.toInputParameter
import io.cloudflight.usecase.PlotAbstractnessInstabilityGraph
import io.cloudflight.usecase.PlotAbstractnessInstabilityGraphImpl

//class Application {

object App {
    const val appName = "DBS CLI App"
    const val version = "0.0.1"
}

private val plotAbstractnessInstabilityGraph: PlotAbstractnessInstabilityGraph =
    PlotAbstractnessInstabilityGraphImpl()

// TODO use https://github.com/Kotlin/kotlinx-cli for smoother cli support
fun main(args: Array<String>) {
    try {
        val inputParameters = args.toInputParameter()

        plotAbstractnessInstabilityGraph.plotAbstractnessInstabilityGraph(inputParameter = inputParameters)
    } catch (exception: Exception) {
        println(exception.message)
    }
}
//}
