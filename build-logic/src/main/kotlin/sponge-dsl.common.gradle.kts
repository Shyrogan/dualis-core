plugins {
    `java-library`
    id("net.kyori.indra.publishing")
    id("org.jetbrains.kotlin.jvm")
}

val libs = project.extensions.getByType(org.gradle.accessors.dm.LibrariesForLibs::class)

dependencies {
    val compileOnly by configurations
    compileOnly(libs.sponge.api)
}
