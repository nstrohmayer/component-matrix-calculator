package io.cloudflight.entity

import java.io.File

data class ModuleDependencies(
    val name: String,
    val basePath: File,
    val ingoingDependencies: MutableList<String> = mutableListOf(),
    val outgoingDependencies: MutableList<String> = mutableListOf()
)
