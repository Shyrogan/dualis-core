plugins {
    id("core.common")
}

dependencies {
    api(libs.kotlin.reflect)
    api(libs.adventure.dsl)
    api(libs.sponge.dsl.api)
    api(libs.sponge.dsl.di)
    api(libs.postgresql)
    api(libs.koin.core)
    api(libs.jedis)
}