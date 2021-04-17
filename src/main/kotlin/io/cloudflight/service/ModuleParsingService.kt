package io.cloudflight.service

import io.cloudflight.entity.GradleModule
import java.io.File

internal interface ModuleParsingService {

    fun getModulesInDirectory(baseDirectory: File): List<GradleModule>

}

internal class ModuleParsingServiceImpl : ModuleParsingService {

    override fun getModulesInDirectory(baseDirectory: File): List<GradleModule> {
        val moduleBaseDirectories = baseDirectory.walkTopDown().maxDepth(1).filter { it.isModule() }.toList()
        val moduleMap = moduleBaseDirectories.map { it.name to GradleModule(name = it.name) }.toMap()

        moduleBaseDirectories.forEach { moduleBaseDirectory ->
            moduleBaseDirectory.getBuildGradleFile()
                    ?.getProjectDependencies()
                    ?.forEach { projectDependencyName ->
                        if (moduleMap.keys.contains(projectDependencyName)) {
                            moduleMap[projectDependencyName]?.ingoingDependencies?.add(projectDependencyName)
                        }

                        if (moduleMap.keys.contains(moduleBaseDirectory.name)) {
                            moduleMap[moduleBaseDirectory.name]?.outgoingDependencies?.add(projectDependencyName)
                        }
                    }
        }

        return moduleMap.values.toList()
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
