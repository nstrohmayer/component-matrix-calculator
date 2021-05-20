package io.cloudflight.service.instability

import application.FileAnalyzerService
import application.FileAnalyzerServiceImpl
import io.cloudflight.utils.containsText
import java.io.File

internal interface AbstractnessCalculationService {

    fun calculateAbstractnessForDirectory(baseDirectory: File): Double

}

internal class AbstractnessCalculationServiceImpl : AbstractnessCalculationService {

    private val fileAnalysisService: FileAnalyzerService = FileAnalyzerServiceImpl()

    override fun calculateAbstractnessForDirectory(baseDirectory: File): Double {
        val allFilesInDirectory = getAllFilesForProject(baseDirectory)

        return calculateAbstractnessForFiles(allFilesInDirectory)
    }

    private fun getAllFilesForProject(projectPath: File): List<File> {
        return projectPath.walkTopDown().toList()
    }

    private fun calculateAbstractnessForFiles(allFilesInDirectory: List<File>): Double {
        var concreteClassesCount = 0
        var abstractClassesCount = 0

        allFilesInDirectory.forEach { file ->
            fileAnalysisService.analyzeFile(file).also {
                concreteClassesCount += it.concreteClassesCount
                abstractClassesCount += it.abstractClassesCount
            }
        }

        return if (abstractClassesCount > 0) {
            abstractClassesCount / concreteClassesCount.toDouble()
        } else {
            0.0
        }
    }

    companion object AbstractFileUtils { // TODO is it a good idea to have this here?
        private val javaFileEnding = ".java"
        private val kotlinFileEnding = ".kt"
        private val abstractKeywords = listOf("interface", "abstract")

        internal fun File.isAbstract(): Boolean {
            return this.isCodeClass() && this.containsAbstractKeywords()
        }

        private fun File.isCodeClass(): Boolean {
            return this.name.endsWith(javaFileEnding, ignoreCase = true) || this.name.endsWith(
                kotlinFileEnding,
                ignoreCase = true
            )
        }

        private fun File.containsAbstractKeywords(): Boolean {
            return this.containsText(*abstractKeywords.toTypedArray())
        }

    }

}

