plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
    maven("https://dl.bintray.com/mipt-npm/dataforge")
    maven("https://dl.bintray.com/mipt-npm/kscience")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation("kscience.plotlykt:plotlykt-server:0.3.0")
}
