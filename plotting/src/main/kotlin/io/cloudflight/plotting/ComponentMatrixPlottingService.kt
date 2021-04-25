package io.cloudflight.plotting

import hep.dataforge.meta.invoke
import io.cloudflight.entities.ComponentMatrixPoint
import kscience.plotly.Plot
import kscience.plotly.Plotly
import kscience.plotly.makeFile
import kscience.plotly.models.ScatterMode
import kscience.plotly.models.TextPosition
import kscience.plotly.scatter

interface ComponentMatrixPlottingService {

    fun plotComponentMatrix(points: List<ComponentMatrixPoint>)

}

class ComponentMatrixPlottingServiceImpl : ComponentMatrixPlottingService {

    override fun plotComponentMatrix(points: List<ComponentMatrixPoint>) {
        val plot = Plotly.plot {
            getModuleScatterPoints(points.toMutableList())
            getMainSequenceScatter()
            getLayout()
        }

        plot.makeFile()
    }

    private fun Plot.getModuleScatterPoints(points: MutableList<ComponentMatrixPoint>) {
        if (points.size == 1) {
            points.add(ComponentMatrixPoint("dummy-point", -1.0, -1.0))
        }

        scatter {
            x.set(points.map { it.instability })
            y.set(points.map { it.abstractness })
            textsList = points.map { it.name }.toList()
            textposition = TextPosition.`top center`
            mode = ScatterMode.`markers+text`
            name = "Modules"
        }
    }

    private fun Plot.getMainSequenceScatter() {
        scatter {
            x(0.0, 1.0)
            y(1.0, 0.0)
            mode = ScatterMode.lines
            name = "Main Sequence"
        }
    }

    private fun Plot.getLayout() {
        layout {
            title = "Abstractness/Instability"
            width = 700
            height = 700
            xaxis {
                title = "Instability"
                range = 0.0..1.0
            }
            yaxis {
                title = "Abstractness"
                range = 0.0..1.0
            }
        }
    }

}
