package application

import UnsupportedFileException
import io.cloudflight.data.TestProjectFilesData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File

internal class FileAnalyzerServiceTest {
    private val fileAnalyzerService: FileAnalyzerService = FileAnalyzerServiceImpl()

    @Nested
    @DisplayName("finding the right analyzer")
    inner class AnalyzerMappingTests {

        @Test
        fun `analyzer map returning the correct analyzer`() {
            val javaFile = File(TestProjectFilesData.JAVA_CONCRETE_CLASS)

            val fileAnalysisResult = fileAnalyzerService.analyzeFile(javaFile)

            assertThat(fileAnalysisResult).isNotNull
        }

        @Test
        fun `unsupported file type throws exception`() {
            val javaFile = File("/file.vue")

            assertThrows<UnsupportedFileException> {
                fileAnalyzerService.analyzeFile(javaFile)
            }
        }
    }

    @Nested
    @DisplayName("generic file analyzer tests")
    inner class GenericFileAnalyzerTests {

        @Test
        fun `concrete class gets detected correctly`() {
            val concreteClass = File(TestProjectFilesData.JAVA_CONCRETE_CLASS)

            val fileAnalysisResult = fileAnalyzerService.analyzeFile(concreteClass)

            assertThat(fileAnalysisResult.concreteClassesCount).isOne
            assertThat(fileAnalysisResult.abstractClassesCount).isZero
        }

        @Test
        fun `abstract class gets detected correctly`() {
            val concreteClass = File(TestProjectFilesData.JAVA_ABSTRACT_CLASS)

            val fileAnalysisResult = fileAnalyzerService.analyzeFile(concreteClass)

            assertThat(fileAnalysisResult.concreteClassesCount).isZero
            assertThat(fileAnalysisResult.abstractClassesCount).isOne
        }

        @Test
        fun `interface gets detected correctly`() {
            val concreteClass = File(TestProjectFilesData.JAVA_INTERFACE)

            val fileAnalysisResult = fileAnalyzerService.analyzeFile(concreteClass)

            assertThat(fileAnalysisResult.concreteClassesCount).isZero
            assertThat(fileAnalysisResult.abstractClassesCount).isOne
        }

        @Test
        fun `concrete and abstract class in one file gets counted correctly`() {
            val concreteClass = File(TestProjectFilesData.JAVA_ABSTRACT_CONCRETE_CLASS)

            val fileAnalysisResult = fileAnalyzerService.analyzeFile(concreteClass)

            assertThat(fileAnalysisResult.concreteClassesCount).isOne
            assertThat(fileAnalysisResult.abstractClassesCount).isOne
        }

        @Test
        fun `keywords in comments do not get counted`() {
            val concreteClass = File(TestProjectFilesData.FILE_WITH_KEYWORDS_IN_COMMENTS)

            val fileAnalysisResult = fileAnalyzerService.analyzeFile(concreteClass)

            assertThat(fileAnalysisResult.concreteClassesCount).isZero
            assertThat(fileAnalysisResult.abstractClassesCount).isZero

        }


    }

}
