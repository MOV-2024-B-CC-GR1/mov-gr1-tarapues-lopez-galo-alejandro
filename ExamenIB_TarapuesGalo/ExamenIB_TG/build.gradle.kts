plugins {
    kotlin("jvm") version "2.0.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.8.6") // Agrega esta línea
}


tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(15)
}

