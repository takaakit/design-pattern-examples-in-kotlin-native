plugins {
    kotlin("multiplatform") version "1.7.20"
}

repositories {
    mavenCentral()
}

kotlin {

    // Currently, allow to work only on Linux.
    val hostOs = System.getProperty("os.name")
    val nativeTarget = when {
        hostOs == "Linux" -> linuxX64("native")
        else -> throw GradleException("$hostOs is not supported.")
    }

    nativeTarget.apply {
        binaries {
            all {
                // Enable the new memory manager.
                // https://github.com/JetBrains/kotlin/blob/master/kotlin-native/NEW_MM.md
                binaryOptions["memoryModel"] = "experimental"
            }

            executable("behavioralPatterns.chainOfResponsibility") {
                entryPoint = "behavioralPatterns.chainOfResponsibility.main"
            }
            executable("behavioralPatterns.command") {
                entryPoint = "behavioralPatterns.command.main"
            }
            executable("behavioralPatterns.interpreter") {
                entryPoint = "behavioralPatterns.interpreter.main"
            }
            executable("behavioralPatterns.iterator") {
                entryPoint = "behavioralPatterns.iterator.main"
            }
            executable("behavioralPatterns.mediator") {
                entryPoint = "behavioralPatterns.mediator.main"
            }
            executable("behavioralPatterns.memento") {
                entryPoint = "behavioralPatterns.memento.main"
            }
            executable("behavioralPatterns.observer") {
                entryPoint = "behavioralPatterns.observer.main"
            }
            executable("behavioralPatterns.state") {
                entryPoint = "behavioralPatterns.state.main"
            }
            executable("behavioralPatterns.strategy") {
                entryPoint = "behavioralPatterns.strategy.main"
            }
            executable("behavioralPatterns.templateMethod") {
                entryPoint = "behavioralPatterns.templateMethod.main"
            }
            executable("behavioralPatterns.visitor") {
                entryPoint = "behavioralPatterns.visitor.main"
            }
            executable("creationalPatterns.abstractFactory") {
                entryPoint = "creationalPatterns.abstractFactory.main"
                runTask?.standardInput = System.`in`
            }
            executable("creationalPatterns.builder") {
                entryPoint = "creationalPatterns.builder.main"
                runTask?.standardInput = System.`in`
            }
            executable("creationalPatterns.factoryMethod") {
                entryPoint = "creationalPatterns.factoryMethod.main"
            }
            executable("creationalPatterns.prototype") {
                entryPoint = "creationalPatterns.prototype.main"
            }
            executable("creationalPatterns.singleton") {
                entryPoint = "creationalPatterns.singleton.main"
            }
            executable("structuralPatterns.adapter") {
                entryPoint = "structuralPatterns.adapter.main"
            }
            executable("structuralPatterns.bridge") {
                entryPoint = "structuralPatterns.bridge.main"
            }
            executable("structuralPatterns.composite") {
                entryPoint = "structuralPatterns.composite.main"
            }
            executable("structuralPatterns.decorator") {
                entryPoint = "structuralPatterns.decorator.main"
            }
            executable("structuralPatterns.facade") {
                entryPoint = "structuralPatterns.facade.main"
            }
            executable("structuralPatterns.flyweight") {
                entryPoint = "structuralPatterns.flyweight.main"
                runTask?.standardInput = System.`in`
            }
            executable("structuralPatterns.proxy") {
                entryPoint = "structuralPatterns.proxy.main"
            }
        }
    }
    sourceSets {
        val nativeMain by getting {
            dependencies {
                // I/O library
                implementation("com.squareup.okio:okio:3.0.0")

                // GTK4 bindings
                // https://gitlab.com/gtk-kt/gtk-kt
                implementation("org.gtk-kt:gtk:1.0.0-alpha1")
                implementation("org.gtk-kt:dsl:0.1.0-alpha0")
                implementation("org.gtk-kt:coroutines:0.1.0-alpha0")
                implementation("org.gtk-kt:ktx:0.1.0-alpha0")
                implementation("org.gtk-kt:cairo:0.1.0-alpha0")
            }
        }
    }
}

