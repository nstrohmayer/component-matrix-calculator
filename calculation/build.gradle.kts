plugins {
    id("org.springframework.boot")

    kotlin("jvm")
}

repositories {
    jcenter()
    maven("https://dl.bintray.com/mipt-npm/dataforge")
    maven("https://dl.bintray.com/mipt-npm/kscience")
    maven("https://dl.bintray.com/mipt-npm/dev")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation(project(":plotting")) // TODO invert dependency + add dependency injection if needed
    // maybe with singleton plottingServiceFactory + plottingImpl in plotting-module and in init() it registers itself in the factory
    implementation("kscience.plotlykt:plotlykt-server:0.3.0") // TODO fix
    implementation("org.springframework.boot:spring-boot-starter")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(project(":test-data"))
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

