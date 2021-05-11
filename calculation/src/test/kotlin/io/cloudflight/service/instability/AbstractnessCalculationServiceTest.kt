package io.cloudflight.service.instability

import io.cloudflight.data.TestProjectFilesData.FIRST_MODULE_SOURCES_BASE_PATH
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

internal class AbstractnessCalculationServiceTest {

    private val abstractnessCalculationService: AbstractnessCalculationService = AbstractnessCalculationServiceImpl()

    @Test
    fun calculateAbstractnessForProject() {
        val testProjectDirectory = File(FIRST_MODULE_SOURCES_BASE_PATH)

        val calculatedAbstractness = abstractnessCalculationService.calculateAbstractnessForDirectory(testProjectDirectory)

        assertThat(calculatedAbstractness).isEqualTo(3.0 / 5) // TODO fix abstract keywords in comments not counting / only count in class definition of the file?
    }

}
