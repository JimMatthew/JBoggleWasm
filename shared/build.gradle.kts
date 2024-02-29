import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
       browser()
    }
    
    jvm()
    
    sourceSets {
        commonMain.dependencies {
            // put your Multiplatform dependencies here
            //implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
            //implementation("io.ktor:ktor-server-content-negotiation-jvm")
            //implementation("io.ktor:ktor-server-core-jvm")
            //implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
                    }
    }
}

