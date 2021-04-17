package io.cloudflight.service.calculation

import io.cloudflight.entity.GradleModule

internal interface InstabilityCalculationService {

    fun calculateInstabilityForModule(module: GradleModule): Double

}

internal class InstabilityCalculationServiceImpl : InstabilityCalculationService {

    override fun calculateInstabilityForModule(module: GradleModule): Double {
        val fanIn = module.ingoingDependencies.size
        val fanOut = module.outgoingDependencies.size

        return if (fanOut != 0) {
            fanOut / (fanOut + fanIn).toDouble()
        } else {
            1.0
        }
    }

}
