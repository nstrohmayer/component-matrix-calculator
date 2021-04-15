package io.cloudflight.service.calculation

import io.cloudflight.utils.isAbstract
import io.cloudflight.utils.isCodeClass
import java.io.File

internal interface AbstractnessCalculationService {

    fun calculateAbstractnessForProject(projectPath: File): Double

}

internal class AbstractnessCalculationServiceImpl : AbstractnessCalculationService {
    override fun calculateAbstractnessForProject(projectPath: File): Double {
        val allFilesInDirectory = getAllFilesForProject(projectPath)

        return calculateAbstractnessForFiles(allFilesInDirectory)
    }

    private fun getAllFilesForProject(projectPath: File): List<File> {
        return projectPath.walkTopDown().toList()
    }

    private fun calculateAbstractnessForFiles(allFilesInDirectory: List<File>): Double {
        val allCodeFilesCount = allFilesInDirectory.filter { it.isCodeClass() }.size
        val abstractFilesCount = allFilesInDirectory.filter { it.isAbstract() }.size

        return if (abstractFilesCount > 0) {
            abstractFilesCount / allCodeFilesCount.toDouble()
        } else {
            0.0
        }
    }


}
