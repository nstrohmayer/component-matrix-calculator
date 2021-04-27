plugins {
    id("org.springframework.boot")

    kotlin("jvm")
}

repositories {
    mavenCentral()
    jcenter()
    maven("https://dl.bintray.com/mipt-npm/dataforge")
    maven("https://dl.bintray.com/mipt-npm/kscience")
    maven("https://dl.bintray.com/mipt-npm/dev")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation("kscience.plotlykt:plotlykt-server:0.3.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks {
    bootJar {
        enabled = false
    }

    jar {
        enabled = true
    }
}
