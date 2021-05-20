package io.cloudflight.usecase

import io.cloudflight.data.TestProjectFilesData
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class PlotModuleDependenciesServiceTest {

    private val plotModuleDependenciesUsecase: PlotModuleDependenciesUsecase = PlotModuleDependenciesService()

    @Test
    fun plotModuleDependencies() {
        val projectBasePath = File(TestProjectFilesData.BASE_PATH)

        plotModuleDependenciesUsecase.plotModuleDependencies(projectBasePath)
    }
}
