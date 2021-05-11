plugins {
    id("org.springframework.boot")

    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("org.springframework.boot:spring-boot-starter")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(project(":test-data"))
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

