package io.cloudflight.converter

import io.cloudflight.entity.InputParameter
import org.springframework.stereotype.Service

interface InputParameterConverter {

    fun Array<String>.toInputParameter(): InputParameter

}

@Service
class InputParameterConverterImpl : InputParameterConverter {

    companion object {
        const val PROJECT_PATH_INDEX = 0;
    }

    override fun Array<String>.toInputParameter(): InputParameter {
        return InputParameter(
                this[PROJECT_PATH_INDEX]
        )
    }

}
