package io.cloudflight.data

object TestProjectFilesData {

    internal const val BASE_PATH = "src/test/testProject"
    internal const val FIRST_MODULE_NAME = "main-module"
    internal const val FIRST_MODULE_BASE_PATH = "$BASE_PATH/$FIRST_MODULE_NAME"
    internal const val FIRST_MODULE_SOURCES_BASE_PATH = "$FIRST_MODULE_BASE_PATH/src/main"
    internal const val FIRST_MODULE_BUILD_GRADLE_FILE = "$FIRST_MODULE_BASE_PATH/build.gradle"
    internal const val SECOND_MODULE_NAME = "second-module"
    internal const val SECOND_MODULE_BASE_PATH = "$BASE_PATH/$SECOND_MODULE_NAME"
    internal const val SECOND_MODULE_SOURCES_BASE_PATH = "$SECOND_MODULE_BASE_PATH/src/main"
    internal const val SECOND_MODULE_BUILD_GRADLE_FILE = "$SECOND_MODULE_SOURCES_BASE_PATH/build.gradle"
    internal const val ABSTRACTNESS_TEST_FILES_BASE_PATH = "$FIRST_MODULE_SOURCES_BASE_PATH/kotlin/io/cloudflight/service/"

}
