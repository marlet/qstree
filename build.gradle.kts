plugins {
    kotlin("jvm") version "1.9.21"
    `maven-publish`
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.0")
    compileOnly("org.springframework:spring-webflux:6.1.14")
    compileOnly("org.springframework:spring-webmvc:6.1.14")
    compileOnly("org.springframework.boot:spring-boot-autoconfigure:3.4.5")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets["main"].allSource)
}

publishing {
    publications {
        create<MavenPublication>("formtree") {
            from(components["java"])
            groupId = "com.sabiko"
            artifactId = "qstree"
            version = "0.1.0"
            artifact(sourcesJar.get())
        }

    }
}
