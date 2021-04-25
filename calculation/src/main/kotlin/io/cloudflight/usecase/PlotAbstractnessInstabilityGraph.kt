package io.cloudflight.usecase

import io.cloudflight.entities.ComponentMatrixPoint
import io.cloudflight.entity.InputParameter
import io.cloudflight.entity.ModuleData
import io.cloudflight.plotting.ComponentMatrixPlottingService
import io.cloudflight.plotting.ComponentMatrixPlottingServiceImpl
import io.cloudflight.service.ComponentMatrixCalculationService
import io.cloudflight.service.ComponentMatrixCalculationServiceImpl

interface PlotAbstractnessInstabilityGraph {

    fun plotAbstractnessInstabilityGraph(inputParameter: InputParameter)

}

internal class PlotAbstractnessInstabilityGraphImpl : PlotAbstractnessInstabilityGraph {

    private val componentMatrixCalculationService: ComponentMatrixCalculationService =
        ComponentMatrixCalculationServiceImpl()
    private val componentMatrixPlottingService: ComponentMatrixPlottingService = ComponentMatrixPlottingServiceImpl()

    override fun plotAbstractnessInstabilityGraph(inputParameter: InputParameter) {
        val moduleData = componentMatrixCalculationService.calculateForProject(inputParameter.projectBasePath)

        componentMatrixPlottingService.plotComponentMatrix(moduleData.toComponentMatrixPoint())
    }

    private fun List<ModuleData>.toComponentMatrixPoint(): List<ComponentMatrixPoint> {
        return this.map { it.toComponentMatrixPoint() }
    }

    private fun ModuleData.toComponentMatrixPoint(): ComponentMatrixPoint {
        return ComponentMatrixPoint(
            name = this.name,
            instability = this.instability,
            abstractness = this.abstractness
        )
    }

}
