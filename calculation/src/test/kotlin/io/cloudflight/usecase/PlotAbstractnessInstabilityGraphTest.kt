package io.cloudflight.usecase

import io.cloudflight.data.TestProjectFilesData
import io.cloudflight.entity.InputParameter
import org.junit.jupiter.api.Test
import java.io.File

internal class PlotAbstractnessInstabilityGraphTest {

    private val plotAbstractnessInstabilityGraph: PlotAbstractnessInstabilityGraph = PlotAbstractnessInstabilityGraphImpl()

    @Test
    fun plotAbstractnessInstabilityGraph() {
        val inputParameter = InputParameter(File(TestProjectFilesData.BASE_PATH))

        plotAbstractnessInstabilityGraph.plotAbstractnessInstabilityGraph(inputParameter)
    }
}
