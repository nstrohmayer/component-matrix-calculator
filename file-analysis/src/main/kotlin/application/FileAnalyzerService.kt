package application

import JavaFileAnalyzer
import KotlinFileAnalyzer
import UnsupportedFileException
import entity.FileAnalysisResult
import java.io.File

interface FileAnalyzerService {

    fun analyzeFile(file: File): FileAnalysisResult

}

// TODO make this internal + expose only interface, is not possible rn because of missing dependency inversion
class FileAnalyzerServiceImpl() : FileAnalyzerService {

    // TODO inject all subclasses of FileAnalyzer
    private val fileAnalyzers: List<FileAnalyzer> = listOf(
        JavaFileAnalyzer(),
        KotlinFileAnalyzer()
    )
    private val fileAnalyzersMap: Map<String, FileAnalyzer> = fileAnalyzers.associateBy { it.compatibleFileEnding }

    override fun analyzeFile(file: File): FileAnalysisResult {
        return findFileAnalyzer(file).analyzeFile(file)
    }

    private fun findFileAnalyzer(file: File): FileAnalyzer {
        return fileAnalyzersMap[file.extension]
            ?: throw UnsupportedFileException("No file analyzer found for file: {${file.name}}")
    }

}
