import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.spring") version "1.6.0"
    kotlin("plugin.jpa") version "1.6.0"
}

group = "ru.grobikon"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2021.0.0"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:3.0.4")
    implementation("org.springframework.cloud:spring-cloud-config-client:3.0.5")
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap:3.1.0")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.cloud:spring-cloud-starter-vault-config:3.1.0")
    implementation("org.springframework.cloud:spring-cloud-starter-bus-amqp:3.1.0")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.0.5")
    implementation("org.springframework.boot:spring-boot-starter-security:2.6.1")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server:2.6.1")    //межсервисной связи с использованием клиента
    implementation("org.springframework.cloud:spring-cloud-starter-sleuth:3.1.0")
    implementation("org.springframework.cloud:spring-cloud-sleuth-zipkin:3.1.0")
    implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j:1.0.0.RELEASE")
    implementation("net.logstash.logback:logstash-logback-encoder:7.0.1")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
