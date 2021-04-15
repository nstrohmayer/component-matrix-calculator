package io.cloudflight.utils

import java.io.File

internal fun File.containsText(vararg searchTexts: String): Boolean { // TODO might have to refactor to increase efficiency (stop after keyword was found)
    val fileText = this.readText()

    searchTexts.forEach {
        if (fileText.contains(it))
            return@containsText true
    }

    return false
}

