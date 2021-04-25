package io.cloudflight.plotting

import io.cloudflight.entities.ComponentMatrixPoint
import org.junit.Test

internal class ComponentMatrixPlottingServiceTest {

    private val componentMatrixPlottingService: ComponentMatrixPlottingService = ComponentMatrixPlottingServiceImpl()

    @Test
    fun `plot basic diagram`() {
        val points = listOf(
//            ComponentMatrixPoint("point1", 0.5, 0.5),
//            ComponentMatrixPoint("point1", -0.5, -0.5),
//            ComponentMatrixPoint("first-module", 0.3, 0.7),
            ComponentMatrixPoint("second-module", 0.1, 0.1)
        )

        componentMatrixPlottingService.plotComponentMatrix(points)
    }
}
