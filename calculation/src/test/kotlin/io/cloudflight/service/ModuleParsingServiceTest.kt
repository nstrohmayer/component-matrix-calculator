package io.cloudflight.service

import io.cloudflight.data.TestProjectFilesData.BASE_PATH
import io.cloudflight.data.TestProjectFilesData.FIRST_MODULE_BASE_PATH
import io.cloudflight.data.TestProjectFilesData.FIRST_MODULE_BUILD_GRADLE_FILE
import io.cloudflight.data.TestProjectFilesData.FIRST_MODULE_NAME
import io.cloudflight.data.TestProjectFilesData.SECOND_MODULE_BASE_PATH
import io.cloudflight.data.TestProjectFilesData.SECOND_MODULE_NAME
import io.cloudflight.service.ModuleParsingServiceImpl.ModuleFileUtils.getProjectDependencies
import io.cloudflight.service.ModuleParsingServiceImpl.ModuleFileUtils.isModule
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File

internal class ModuleParsingServiceTest {

    private val moduleParsingService: ModuleParsingService = ModuleParsingServiceImpl()

    @Test
    fun `gradle module directory is detected as module correctly`() {
        val moduleDirectory = File(FIRST_MODULE_BASE_PATH)

        val isModule = moduleDirectory.isModule()

        assertThat(isModule).isTrue
    }

    @Test
    fun `non gradle module is not detected as module`() {
        val moduleDirectory = File(SECOND_MODULE_BASE_PATH)

        val isModule = moduleDirectory.isModule()

        assertThat(isModule).isTrue
    }

    // TODO add tests for getting build gradle file (when the location/naming might differ)

    @DisplayName("getting project dependencies")
    @Nested
    inner class GettingProjectDependenciesTests {

        @Test
        fun `works correctly for build gradle files`() {
            val buildGradleFile = File(FIRST_MODULE_BUILD_GRADLE_FILE)

            val projectDependencies = buildGradleFile.getProjectDependencies()

            assertThat(projectDependencies).hasSize(2)
        }

        @Test
        fun `dependency name gets resolved correctly`() {
            val buildGradleFile = File(FIRST_MODULE_BUILD_GRADLE_FILE)

            val projectDependencies = buildGradleFile.getProjectDependencies()

            assertThat(projectDependencies).contains("second-module")
        }
    }

    @DisplayName("get modules in directory")
    @Nested
    inner class GetModulesInDirectoryTests {

        @Test
        fun `detects correct amount of gradle modules`() {
            val firstModuleBaseDirectory = File(BASE_PATH)

            val gradleModulesForFirstModule = moduleParsingService.getModulesInDirectory(firstModuleBaseDirectory)

            assertThat(gradleModulesForFirstModule).hasSize(2)
        }

        @Test
        fun `finds outgoing dependencies`() {
            val firstModuleBaseDirectory = File(BASE_PATH)

            val gradleModulesForFirstModule = moduleParsingService.getModulesInDirectory(firstModuleBaseDirectory)

            assertThat(gradleModulesForFirstModule.find { it.name == FIRST_MODULE_NAME }?.outgoingDependencies)
                    .containsExactlyInAnyOrder("second-module", "third-module")
            assertThat(gradleModulesForFirstModule.find { it.name == SECOND_MODULE_NAME }?.outgoingDependencies)
                    .containsExactlyInAnyOrder("main-module")
        }

        @Test
        fun `finds ingoing dependencies`() {
            val firstModuleBaseDirectory = File(BASE_PATH)

            val gradleModulesForFirstModule = moduleParsingService.getModulesInDirectory(firstModuleBaseDirectory)

            assertThat(gradleModulesForFirstModule.find { it.name == FIRST_MODULE_NAME }?.ingoingDependencies)
                    .containsExactlyInAnyOrder("second-module")
            assertThat(gradleModulesForFirstModule.find { it.name == SECOND_MODULE_NAME }?.ingoingDependencies)
                    .containsExactlyInAnyOrder("main-module")
        }
    }
}
