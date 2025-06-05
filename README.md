# Java Application Packaging

This repository demonstrates how to package Java applications using modern packaging tools:

- **GraalVM Native Image** for creating native CLI executables
- **jpackage** for creating platform-specific GUI application installers

## Projects

### 1. Native Image CLI (`native-image/`)

A simple command-line tool (`jls`) that lists directory contents, compiled to native executables using GraalVM.

**Features:**
- Zero-dependency native executable
- Fast startup time
- Cross-platform (Linux, macOS, Windows)

**Build:**
```bash
cd native-image
./gradlew nativeCompile
```

**Run:**
```bash
./build/native/nativeCompile/jls [directory]
```

### 2. jpackage GUI App (`jpackage/`)

A simple text viewer GUI application (`SimpleNotepad`) packaged as platform-specific installers using jpackage.

**Features:**
- Native look and feel
- Platform-specific installers (.dmg for macOS, .msi for Windows)
- Self-contained application bundles

**Build:**
```bash
cd jpackage
./gradlew jpackage
```

**Installers generated in:**
- macOS: `build/jpackage/*.dmg`
- Windows: `build/jpackage/*.msi`

## Requirements

- **Java 24** (Oracle JDK recommended)
- **GraalVM** (for native-image project)
- **Platform-specific tools:**
  - macOS: Xcode command line tools
  - Windows: Visual Studio Build Tools

## CI/CD

GitHub Actions workflows automatically build and release both applications:

- **Native Image**: Creates native executables for Linux, macOS, and Windows
- **jpackage**: Creates installers for macOS (.dmg) and Windows (.msi)

Releases are triggered by pushing version tags (e.g., `v1.0.0`).

## Project Structure

```
├── native-image/          # GraalVM native image CLI
│   ├── src/main/java/
│   │   └── com/example/
│   │       └── LsCommand.java
│   └── build.gradle       # GraalVM configuration
├── jpackage/             # jpackage GUI application
│   ├── src/main/java/
│   │   ├── com/example/
│   │   │   └── SimpleNotepad.java
│   │   └── module-info.java
│   └── build.gradle      # jpackage configuration
└── .github/workflows/    # CI/CD workflows
    ├── release-native-image.yml
    └── release-jpackage.yml
```
