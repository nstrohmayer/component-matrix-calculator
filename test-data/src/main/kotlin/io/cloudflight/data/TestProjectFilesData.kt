package io.cloudflight.data

object TestProjectFilesData {

    const val BASE_PATH = "../test-data/src/resources/testProject"
    const val FIRST_MODULE_NAME = "main-module"
    const val FIRST_MODULE_BASE_PATH = "$BASE_PATH/$FIRST_MODULE_NAME"
    const val FIRST_MODULE_SOURCES_BASE_PATH = "$FIRST_MODULE_BASE_PATH/src/main"
    const val FIRST_MODULE_BUILD_GRADLE_FILE = "$FIRST_MODULE_BASE_PATH/build.gradle"
    const val SECOND_MODULE_NAME = "second-module"
    const val SECOND_MODULE_BASE_PATH = "$BASE_PATH/$SECOND_MODULE_NAME"
    const val SECOND_MODULE_SOURCES_BASE_PATH = "$SECOND_MODULE_BASE_PATH/src/main"
    const val SECOND_MODULE_BUILD_GRADLE_FILE = "$SECOND_MODULE_SOURCES_BASE_PATH/build.gradle"
    const val ABSTRACTNESS_TEST_FILES_BASE_PATH = "$FIRST_MODULE_SOURCES_BASE_PATH/kotlin/io/cloudflight/service/"

    private const val JAVA_CLASSES_BASE_PATH = "$FIRST_MODULE_SOURCES_BASE_PATH/java"
    const val JAVA_CONCRETE_CLASS = "$JAVA_CLASSES_BASE_PATH/ConcreteClass.java"
    const val JAVA_ABSTRACT_CLASS = "$JAVA_CLASSES_BASE_PATH/AbstractClass.java"
    const val JAVA_INTERFACE = "$JAVA_CLASSES_BASE_PATH/Interface.java"
    const val JAVA_ABSTRACT_CONCRETE_CLASS = "$JAVA_CLASSES_BASE_PATH/ConcreteAndAbstractClass.java"
    const val FILE_WITH_KEYWORDS_IN_COMMENTS = "$JAVA_CLASSES_BASE_PATH/FileWithKeywordsInComments.java"

}
