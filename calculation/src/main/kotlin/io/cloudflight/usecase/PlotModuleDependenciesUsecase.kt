package io.cloudflight.usecase

import io.cloudflight.entity.ModuleDependencies
import io.cloudflight.service.ModuleParsingService
import io.cloudflight.service.ModuleParsingServiceImpl
import java.io.File

interface PlotModuleDependenciesUsecase {

    fun plotModuleDependencies(projectPath: File)

//    fun plotModuleDependencies(moduleDependencies: List<ModuleDependencies>) option to print both component matrix calculation service + plantuml

}

internal class PlotModuleDependenciesService : PlotModuleDependenciesUsecase {

    private val moduleParsingService: ModuleParsingService = ModuleParsingServiceImpl()

    override fun plotModuleDependencies(projectPath: File) {
        val moduleDependencies = moduleParsingService.getModulesInDirectory(projectPath)
        val fileName = "${projectPath.name}.puml"

        plotModuleDependencies(fileName, moduleDependencies)
    }

    private fun plotModuleDependencies(fileName: String, moduleDependencies: List<ModuleDependencies>) {
        File(fileName).printWriter().use { printWriter ->
            printWriter.println("@startuml")

            moduleDependencies.forEach { moduleDependencies ->
                printWriter.println(moduleDependencies.name.toPlantUmlComponent())

                moduleDependencies.outgoingDependencies.forEach { outgoingDependency ->
                    printWriter.println(moduleDependencies.name.toPlantUmlComponent() + " --> " + outgoingDependency.toPlantUmlComponent())
                }
            }

            printWriter.println("@enduml")
        }
    }

    private fun String.toPlantUmlComponent(): String = "[$this]"

}
