plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.4.5"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("plugin.jpa") version "1.9.25"
}

group = "com.b1nd"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    //database
    implementation("io.asyncer:r2dbc-mysql:1.4.1")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")

    //security
    implementation("org.springframework.boot:spring-boot-starter-security")

    //coroutine
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.10.2")

    //test
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
