package io.cloudflight.entity

import java.io.File

data class ModuleData(
        val name: String,
        val basePath: File,
        val ingoingDependencies: List<String>,
        val outgoingDependencies: List<String>,
        val instability: Double,
        val abstractness: Double
)
