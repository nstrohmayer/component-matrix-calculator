package io.cloudflight.service

import io.cloudflight.entity.ModuleDependencies
import java.io.File

internal interface ModuleParsingService {

    fun getModulesInDirectory(baseDirectory: File): List<ModuleDependencies>

}

internal class ModuleParsingServiceImpl : ModuleParsingService {

    override fun getModulesInDirectory(baseDirectory: File): List<ModuleDependencies> {
        val moduleBaseDirectories = getModulesBaseDirectory(baseDirectory)
        val moduleMap = moduleBaseDirectories
                .map { it.name to ModuleDependencies(name = it.name, basePath = it) }.toMap()

        moduleMap.values.forEach { moduleDependency ->
            moduleDependency.basePath.getBuildGradleFile()
                    ?.getProjectDependencies()
                    ?.forEach { dependencyName ->
                        addToOwnOutgoingDependencies(moduleDependency, dependencyName)
                        addToIngoingDependencies(moduleMap, moduleDependency, dependencyName)
                    }
        }

        return moduleMap.values.toList()
    }

    private fun getModulesBaseDirectory(baseDirectory: File): List<File> {
        return baseDirectory
                .walkTopDown()
                .maxDepth(1)
                .filter { it.isModule() }
                .toList()
    }

    private fun addToOwnOutgoingDependencies(moduleDependencies: ModuleDependencies, dependencyName: String) {
        moduleDependencies.outgoingDependencies.add(dependencyName)
    }

    private fun addToIngoingDependencies(moduleMap: Map<String, ModuleDependencies>, moduleDependency: ModuleDependencies, dependencyName: String) {
        if (moduleMap.keys.contains(dependencyName)) {
            moduleMap[dependencyName]?.ingoingDependencies?.add(moduleDependency.name)
        }
    }

    companion object ModuleFileUtils {

        private const val buildGradleFileName = "build.gradle"

        fun File.isModule(): Boolean {
            return isDirectory && this.listFiles()?.map { it.name }?.contains(buildGradleFileName) ?: false
        }

        fun File.getBuildGradleFile(): File? {
            return listFiles()?.first { it.name == buildGradleFileName }
        }

        fun File.getProjectDependencies(): List<String> {
            return readLines().filter {
                (it.contains("api") || it.contains("implementation"))
                        && it.contains("project")
                        && it.contains("':")
            }.map {
                it.trim()
                        .split("'")[1]
                        .removePrefix(":")
            }
        }

    }
}
