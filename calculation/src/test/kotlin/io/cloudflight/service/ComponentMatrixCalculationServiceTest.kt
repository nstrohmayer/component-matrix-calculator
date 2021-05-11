package io.cloudflight.service

import io.cloudflight.data.TestProjectFilesData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File

internal class ComponentMatrixCalculationServiceTest {

    private val componentMatrixCalculationService: ComponentMatrixCalculationService =
        ComponentMatrixCalculationServiceImpl()

    @DisplayName("calculate for project")
    @Nested
    inner class CalculateForProjectTests {

        @Test
        fun `asdf`() { // TODO find better name
            val testProjectBasePath = File(TestProjectFilesData.BASE_PATH)

            val moduleDataList = componentMatrixCalculationService.calculateForProject(testProjectBasePath)

            assertThat(moduleDataList).hasSize(2)
            moduleDataList[0].apply {
                assertThat(name).isEqualTo("main-module")
                assertThat(basePath).isEqualTo(File(TestProjectFilesData.FIRST_MODULE_BASE_PATH))
                assertThat(outgoingDependencies).containsExactlyInAnyOrder("second-module", "third-module")
                assertThat(ingoingDependencies).containsExactlyInAnyOrder("second-module")
                assertThat(instability).isBetween(0.0, 1.0)
                assertThat(abstractness).isBetween(0.0, 1.0)
            }
            moduleDataList[1].apply {
                assertThat(name).isEqualTo("second-module")
                assertThat(basePath).isEqualTo(File(TestProjectFilesData.SECOND_MODULE_BASE_PATH))
                assertThat(outgoingDependencies).containsExactlyInAnyOrder("main-module")
                assertThat(ingoingDependencies).containsExactlyInAnyOrder("main-module")
                assertThat(instability).isBetween(0.0, 1.0)
                assertThat(abstractness).isBetween(0.0, 1.0)
            }
        }
    }
}
