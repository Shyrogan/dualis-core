import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.spongepowered.gradle.plugin.config.PluginLoaders
import org.spongepowered.plugin.metadata.model.PluginDependency

plugins {
    id("core.common")
    alias(libs.plugins.sponge.gradle)
    alias(libs.plugins.shadow)
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    api(projects.dualisCoreApi)
    implementation(libs.hikari)
}

sponge {
    apiVersion("9.0.0")
    license("PRIVATE")
    loader {
        name(PluginLoaders.JAVA_PLAIN)
        version("1.0")
    }
    plugin("dualis-core") {
        displayName("Core")
        entrypoint("games.dualis.core.CorePlugin")
        description("A core library used by developers.")
        contributor("Shyrogan") {
            description("Lead Developer")
        }
        dependency("spongeapi") {
            loadOrder(PluginDependency.LoadOrder.AFTER)
            optional(false)
        }
    }
}

tasks.named<ShadowJar>("shadowJar").configure {
    minimize()
    dependencies {
        exclude {
            setOf("org.slf4j", "net.kyori").contains(it.moduleGroup)
        }
    }
}