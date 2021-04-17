package io.cloudflight.service

import io.cloudflight.entity.ModuleData
import io.cloudflight.entity.ModuleDependencies
import io.cloudflight.service.calculation.AbstractnessCalculationService
import io.cloudflight.service.calculation.AbstractnessCalculationServiceImpl
import io.cloudflight.service.calculation.InstabilityCalculationService
import io.cloudflight.service.calculation.InstabilityCalculationServiceImpl
import java.io.File

interface ComponentMatrixCalculationService {

    fun calculateForProject(projectPath: File): List<ModuleData> // TODO find a better name

    fun plotComponentMatrix() // TODO

}

class ComponentMatrixCalculationServiceImpl : ComponentMatrixCalculationService {

    private val instabilityCalculationService: InstabilityCalculationService = InstabilityCalculationServiceImpl()
    private val abstractnessCalculationService: AbstractnessCalculationService = AbstractnessCalculationServiceImpl()
    private val moduleParsingService: ModuleParsingService = ModuleParsingServiceImpl()

    override fun calculateForProject(projectPath: File): List<ModuleData> {
        return moduleParsingService.getModulesInDirectory(projectPath)
                .map {
                    val instability = instabilityCalculationService.calculateInstabilityForModule(it)
                    val abstractness = abstractnessCalculationService.calculateAbstractnessForDirectory(it.basePath)

                    it.toModuleData(instability, abstractness)
                }
    }

    private fun ModuleDependencies.toModuleData(instability: Double, abstractness: Double) = ModuleData(
            name = this.name,
            basePath = this.basePath,
            ingoingDependencies = this.ingoingDependencies.toList(),
            outgoingDependencies = this.outgoingDependencies.toList(),
            instability = instability,
            abstractness = abstractness
    )

    override fun plotComponentMatrix() {
        TODO("Not yet implemented")
    }

}
