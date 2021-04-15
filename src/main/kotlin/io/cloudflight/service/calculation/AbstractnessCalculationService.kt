package io.cloudflight.service

import io.cloudflight.entity.InputParameter
import org.springframework.stereotype.Service

interface ComponentMatrixCalculationService { // TODO split into different services if they get bigger

    fun calculateAbstractness(inputParameter: InputParameter): Double

    fun calculateInstability(inputParameter: InputParameter): Double

    fun plotComponentMatrix() // TODO

}

@Service
class ComponentMatrixCalculationServiceImpl : ComponentMatrixCalculationService {
    override fun calculateAbstractness(inputParameter: InputParameter): Double {
        TODO("Not yet implemented")
    }

    override fun calculateInstability(inputParameter: InputParameter): Double {
        TODO("Not yet implemented")
    }

    override fun plotComponentMatrix() {
        TODO("Not yet implemented")
    }

}
