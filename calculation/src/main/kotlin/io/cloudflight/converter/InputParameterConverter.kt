package io.cloudflight.converter

import io.cloudflight.InvalidProjectBasePathException
import io.cloudflight.entity.InputParameter
import java.io.File

const val PROJECT_PATH_INDEX = 0

internal fun Array<String>.toInputParameter(): InputParameter {
    val projectBasePath = File(this[PROJECT_PATH_INDEX])

    if (!projectBasePath.isDirectory) {
        throw InvalidProjectBasePathException("The passed path ($projectBasePath) is not a directory!")
    }

    return InputParameter(projectBasePath = projectBasePath)
}
