package io.cloudflight.service.calculation

import java.io.File

internal interface InstabilityCalculationService {

    fun calculateInstabilityForDirectory(baseDirectory: File): Double

}

internal class InstabilityCalculationServiceImpl : InstabilityCalculationService {

    override fun calculateInstabilityForDirectory(baseDirectory: File): Double {
//        val allFilesInDirectory = getAllFilesForProject(baseDirectory)
//
//        return calculateAbstractnessForFiles(allFilesInDirectory)
        return 0.0
    }

//    private fun getAllFilesForProject(projectPath: File): List<File> {
//        return projectPath.walkTopDown().toList()
//    }
//
//    private fun calculateAbstractnessForFiles(allFilesInDirectory: List<File>): Double {
//        val allCodeFilesCount = allFilesInDirectory.filter { it.isCodeClass() }.size
//        val abstractFilesCount = allFilesInDirectory.filter { it.isAbstract() }.size
//
//        return if (abstractFilesCount > 0) {
//            abstractFilesCount / allCodeFilesCount.toDouble()
//        } else {
//            0.0
//        }
//    }
//

}
