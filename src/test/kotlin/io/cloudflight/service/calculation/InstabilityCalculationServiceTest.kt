package io.cloudflight.service.calculation

import io.cloudflight.entity.ModuleDependencies
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.File

@DisplayName("calculating instability")
internal class InstabilityCalculationServiceTest {

    private val instabilityCalculationService: InstabilityCalculationService = InstabilityCalculationServiceImpl()

    @Test
    fun `for module with equal number of in- and outgoing dependencies`() {
        val module = getModuleWithDependencies(2, 2)

        val instability = instabilityCalculationService.calculateInstabilityForModule(module)

        assertThat(instability).isEqualTo(0.5)
    }

    @Test
    fun `for module without ingoing dependencies`() {
        val module = getModuleWithDependencies(0, 2)

        val instability = instabilityCalculationService.calculateInstabilityForModule(module)

        assertThat(instability).isEqualTo(1.0)
    }

    @Test
    fun `for module without outgoing dependencies`() {
        val module = getModuleWithDependencies(0, 0)

        val instability = instabilityCalculationService.calculateInstabilityForModule(module)

        assertThat(instability).isEqualTo(1.0)
    }

    @Test
    fun `for module without in- or outgoing dependencies`() {
        val module = getModuleWithDependencies(0, 0)

        val instability = instabilityCalculationService.calculateInstabilityForModule(module)

        assertThat(instability).isEqualTo(1.0)
    }

    private fun getModuleWithDependencies(ingoingDependencies: Int, outgoingDependencies: Int): ModuleDependencies {
        return ModuleDependencies(
                name = "",
                basePath = File(""),
                ingoingDependencies = (1..ingoingDependencies).map { "" }.toMutableList(),
                outgoingDependencies = (1..outgoingDependencies).map { "" }.toMutableList()
        )
    }
}
