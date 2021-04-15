package io.cloudflight.entity

data class GradleModule(
        val name: String,
        val ingoingDependencies: MutableList<String> = mutableListOf(),
        val outgoingDependencies: MutableList<String> = mutableListOf()
)
