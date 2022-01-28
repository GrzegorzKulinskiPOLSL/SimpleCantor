plugins {
    kotlin("jvm") version "1.5.21"
    id("org.springframework.boot") version "2.6.3"
    id("io.spring.dependency-management")  version "1.0.11.RELEASE"
    id("java")
    id("application")
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "pl.projekt"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_16

repositories {
    mavenCentral()
}
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.6.2")
    implementation("org.springframework.boot:spring-boot-starter-web:2.6.2")
    implementation("org.projectlombok:lombok:1.18.22")
    implementation("org.decimal4j:decimal4j:1.0.3")
    runtimeOnly("org.postgresql:postgresql:42.3.1")
//    Security
    implementation("org.springframework.boot:spring-boot-starter-security:2.6.2")
    implementation("io.jsonwebtoken:jjwt:0.9.1")

    annotationProcessor("org.projectlombok:lombok:1.18.22")

    testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "pl.projekt.simplecantor.SimpleCantorApplication"
    }
    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    isZip64 = true
}
