import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    id("org.springframework.boot") version "2.2.0.RELEASE" apply false
    id("io.spring.dependency-management") version "1.0.8.RELEASE" apply false
    kotlin("jvm") version "1.4.32" apply false
    kotlin("plugin.spring") version "1.3.50" apply false
}

allprojects {
    group = "io.cloudflight"
    version = "1.0.0"

    tasks.withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }
}

subprojects {
    repositories {
        mavenCentral()
    }

    apply {
        plugin("io.spring.dependency-management")
    }
}


//buildscript {
//    repositories {
//        mavenCentral()
//        jcenter()
//    }
//
//    dependencies {
//        classpath "org.jfrog.buildinfo:build-info-extractor-gradle:latest.release"
//    }
//
//}
//
//plugins {
//    id 'org.springframework.boot' version '2.4.4'
//    id 'io.spring.dependency-management' version '1.0.11.RELEASE'
//    id 'groovy'
//    id "org.jetbrains.kotlin.jvm" version "1.4.32"
//}
//
//group = 'io.cloudflight'
//version = '0.0.1-SNAPSHOT'
//sourceCompatibility = '11'
//
////repositories {
////    mavenCentral()
////}
//
//dependencies {
//    implementation 'org.springframework.boot:spring-boot-starter'
//    implementation 'org.codehaus.groovy:groovy'
//    testImplementation 'org.springframework.boot:spring-boot-starter-test'
//}
//
//test {
//    useJUnitPlatform()
//}
//
//tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile).all {
//    kotlinOptions {
//        jvmTarget = "1.8"
//    }
//}
