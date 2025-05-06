import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    api(project(":frame-gateway-api"))

    implementation("ru.kotlix:frame-parties-client-starter")
    implementation("ru.kotlix:frame-auth-api")
    implementation("ru.kotlix:frame-auth-client-starter")
    implementation("ru.kotlix:frame-voice-client-starter")
    implementation("ru.kotlix:frame-state-client-starter")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.springframework:spring-context-support")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("org.springframework.boot:spring-boot-starter-security")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
}

tasks.getByName<BootJar>("bootJar") {
    enabled = true
}

tasks.getByName<Jar>("jar") {
    enabled = false
}
