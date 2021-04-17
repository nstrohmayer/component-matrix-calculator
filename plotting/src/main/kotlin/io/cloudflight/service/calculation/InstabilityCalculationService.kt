package io.cloudflight.service.calculation

import io.cloudflight.entity.ModuleDependencies

internal interface InstabilityCalculationService {

    fun calculateInstabilityForModule(module: ModuleDependencies): Double

}

internal class InstabilityCalculationServiceImpl : InstabilityCalculationService {

    override fun calculateInstabilityForModule(module: ModuleDependencies): Double {
        val fanIn = module.ingoingDependencies.size
        val fanOut = module.outgoingDependencies.size

        return if (fanOut != 0) {
            fanOut / (fanOut + fanIn).toDouble()
        } else {
            1.0
        }
    }

}
