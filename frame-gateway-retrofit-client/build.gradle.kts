import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    api(project(":frame-gateway-api"))

    implementation("com.squareup.retrofit2:retrofit")
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}
