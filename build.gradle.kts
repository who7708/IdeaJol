plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.13.2"
}

group = "com.github.stokito.IdeaJol"
version = "1.11.1"

repositories {
    maven("https://maven.aliyun.com/repository/public")
    maven("https://maven.aliyun.com/repository/google")
    maven("https://maven.aliyun.com/repository/gradle-plugin")
    mavenLocal()
    // mavenCentral()
}

dependencies {
    implementation("org.openjdk.jol:jol-core:0.17")
}

intellij {
    version.set("2022.3.1")
    type.set("IC")

    plugins.set(listOf("java"))
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }

    patchPluginXml {
        sinceBuild.set("223")
        untilBuild.set("231.*")
    }
    //    runPluginVerifier {
    //        ideVersions.set(properties("pluginVerifierIdeVersions").split(',').map(String::trim).filter(String::isNotEmpty))
    //    }
    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
