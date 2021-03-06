import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.spring") version "1.6.0"
}

group = "ru.grobikon"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2021.0.0"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:3.0.4")
    implementation("org.springframework.cloud:spring-cloud-config-client:3.0.5")
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap:3.1.0")
    implementation("org.springframework.cloud:spring-cloud-starter-vault-config:3.1.0")
    implementation("org.springframework.cloud:spring-cloud-starter-bus-amqp:3.0.3")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-oauth2-resource-server:5.6.0")
    implementation("org.springframework.security:spring-security-oauth2-jose:5.6.0")
    implementation("org.springframework.cloud:spring-cloud-starter-sleuth:3.1.0")
    implementation("org.springframework.cloud:spring-cloud-sleuth-zipkin:3.1.0")
    implementation("net.logstash.logback:logstash-logback-encoder:7.0.1")
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
