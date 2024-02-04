plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

val lombokVersion = "1.18.30"
val httpclientVersion = "4.5.14"
val jacksonVersion = "2.16.1"
val junitPlatformVersion = "5.9.1"
val assertjVersion = "3.25.2"
val log4jVersion = "1.2.17"

dependencies {
    testCompileOnly("org.projectlombok:lombok:$lombokVersion")
    testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")
    testImplementation("log4j:log4j:$log4jVersion")
    testImplementation("org.apache.httpcomponents:httpclient:$httpclientVersion")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    testImplementation(platform("org.junit:junit-bom:$junitPlatformVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:$assertjVersion")
}

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}