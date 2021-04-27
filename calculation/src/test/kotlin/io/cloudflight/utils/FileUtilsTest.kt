package io.cloudflight.utils

import io.cloudflight.data.TestProjectFilesData.ABSTRACTNESS_TEST_FILES_BASE_PATH
import io.cloudflight.service.calculation.AbstractnessCalculationServiceImpl.AbstractFileUtils.isAbstract
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File

internal class FileUtilsTest {

    @DisplayName("isAbstract")
    @Nested
    inner class IsAbstractTests {

        @Test
        fun concreteClass() {
            val concreteClass = getTestClass("KotlinConcreteClass.kt")

            val isAbstract = concreteClass.isAbstract()

            assertThat(isAbstract).isFalse
        }

        @Test
        fun `concrete class with abstract in comments`() {
            val concreteClass = getTestClass("KotlinConcreteClass.kt")

            val isAbstract = concreteClass.isAbstract()

            assertThat(isAbstract).isFalse
        }

        @Test
        fun `abstract class`() {
            val abstractClass = getTestClass("KotlinAbstractClass.kt")

            val isAbstract = abstractClass.isAbstract()

            assertThat(isAbstract).isTrue
        }

        @Test
        fun `interface`() {
            val concreteClass = getTestClass("KotlinInterface.kt")

            val isAbstract = concreteClass.isAbstract()

            assertThat(isAbstract).isTrue
        }

        private fun getTestClass(fileName: String): File {
            return File(ABSTRACTNESS_TEST_FILES_BASE_PATH + fileName)
        }
    }
}
