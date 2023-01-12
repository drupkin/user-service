import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.1"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    id("com.github.davidmc24.gradle.plugin.avro") version "1.5.0"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
    kotlin("plugin.allopen") version "1.6.21"
    kotlin("kapt") version "1.6.21"
}

group = "bg.dr.chillywe"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
    maven {
        url = uri("https://packages.confluent.io/maven/")
    }
}

dependencies {
    // Spring
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-mustache")
    implementation("org.springframework.boot:spring-boot-starter-web")
//    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    kapt("org.springframework.boot:spring-boot-configuration-processor")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    // Postgres
    runtimeOnly("org.postgresql:postgresql")

    // Apache avro
    implementation("org.apache.avro:avro:1.11.1")
    implementation("io.confluent:kafka-avro-serializer:7.3.1")

    // Kafka
    implementation("org.springframework.kafka:spring-kafka:3.0.1")

    // Keycloak
//    implementation("org.keycloak:keycloak-spring-boot-starter:20.0.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
        exclude(module = "mockito-core")
    }
    testImplementation("io.kotest:kotest-assertions-core:5.5.4")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("com.ninja-squad:springmockk:3.1.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<com.github.davidmc24.gradle.plugin.avro.GenerateAvroJavaTask> {
    setSource(file("${projectDir}/dev_help/spec/"))
    setOutputDir(file("${projectDir}/build/generated/avro"))

    avro{
        fieldVisibility.set("PRIVATE")
        outputCharacterEncoding.set("UTF-8")
    }
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}