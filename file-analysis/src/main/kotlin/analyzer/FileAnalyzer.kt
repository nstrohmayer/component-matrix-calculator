package application

import UnsupportedFileException
import entity.FileAnalysisResult
import java.io.File

interface FileAnalyzer {

    val compatibleFileEnding: String

    fun analyzeFile(file: File): FileAnalysisResult

}

internal abstract class AbstractFileAnalyzer : FileAnalyzer {

    abstract val concreteKeyword: String
    abstract val abstractKeywords: List<String>
    abstract val commentStart: String

    override fun analyzeFile(file: File): FileAnalysisResult {
        validateFileIsSupported(file)

        var concreteClassesCount = 0
        var abstractClassesCount = 0

        file.readLines().forEach { line -> // TODO add flag to stop searching for keywords after one was found
            if (line.isAbstractClass()) {
                abstractClassesCount++
            } else if (line.isConcreteClass()) {
                concreteClassesCount++
            }
        }

        return FileAnalysisResult(
            abstractClassesCount = abstractClassesCount,
            concreteClassesCount = concreteClassesCount
        )
    }

    private fun validateFileIsSupported(file: File) {
        if (!compatibleFileEnding.endsWith(compatibleFileEnding)) {
            throw UnsupportedFileException("File {${file.name}} is unsupported, following filetypes are supported by this analyzer: {$compatibleFileEnding}")
        }
    }

    private fun String.isConcreteClass(): Boolean {
        return this.sanitize().contains(concreteKeyword)
    }

    private fun String.isAbstractClass(): Boolean {
        val sanitizedLine = this.sanitize()

        abstractKeywords.forEach { abstractKeyword ->
            if (sanitizedLine.contains(abstractKeyword)) {
                return true
            }
        }

        return false
    }

    private fun String.sanitize(): String {
        return if (this.contains(commentStart)) {
            split(commentStart)[0]
        } else {
            this
        }
    }

}
