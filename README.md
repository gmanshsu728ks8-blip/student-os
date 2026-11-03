# Student-OS

A study-management Android app for Egyptian Thanaweya Amma students.
Arabic-first, RTL, dark theme with black and gold design tokens.

> **Status: Phase 1 (foundation).** Only the project skeleton and an honest dashboard exist.
> Subjects, planner, timer, tasks, mistake notebook, exams and analytics are **not implemented**;
> the dashboard shows them as clearly labelled, non-interactive placeholders.

## Requirements

| Tool | Version |
|---|---|
| JDK | 17 or newer (Android Studio's bundled JBR 17/21 works) |
| Android Studio | Ladybug (2024.2) or newer, recommended |
| Android SDK | Platform 35 (`compileSdk`/`targetSdk` 35), minSdk 26 (Android 8.0) |
| Gradle | 8.9 (via the wrapper) |

## Build and run

### Android Studio (recommended)
1. **File > Open** and select this folder. Let Gradle sync finish (it downloads Gradle 8.9 and dependencies).
2. Install **Android SDK Platform 35** if Studio prompts you.
3. Pick an emulator or a connected device (Android 8.0+) and press **Run**.

### Command line
The Gradle wrapper script and JAR (`gradlew`, `gradle/wrapper/gradle-wrapper.jar`) are **not committed yet**
because they could not be generated in the offline environment where Phase 1 was written.
Generate them once with any installed Gradle 8.9+:

```bash
gradle wrapper --gradle-version 8.9
```

Then:

```bash
export ANDROID_HOME=/path/to/Android/Sdk      # or set sdk.dir in local.properties
./gradlew assembleDebug          # APK at app/build/outputs/apk/debug/
./gradlew testDebugUnitTest      # JVM unit tests
./gradlew installDebug           # install on a connected device/emulator
```

## Project structure

```
app/src/main/java/com/studentos/app/
  MainActivity.kt            Single activity, edge-to-edge, hosts the nav graph
  navigation/                Destinations + NavHost (only Dashboard for now)
  feature/dashboard/         DashboardScreen (UI), DashboardViewModel, DashboardUiState
  ui/theme/                  Color, Type, Dimens (spacing/shapes), Theme  <- all design tokens
  ui/components/             Reusable composables: SectionCard, StatTile, PlaceholderCard, ...
app/src/main/res/            Arabic strings (default), English strings (values-en), launcher icon
app/src/test/                Unit tests (DashboardViewModel)
gradle/libs.versions.toml    Version catalog
```

## Design decisions

- **Single module.** Splitting into modules adds build cost without benefit at this size. Packages are organized by
  feature (`feature/...`) so they can be extracted into modules later if needed.
- **MVVM with unidirectional data flow.** The ViewModel exposes an immutable `StateFlow<UiState>`;
  composables are stateless (`DashboardContent`) and previewable.
- **Arabic-first.** Arabic is the *default* resource set (`values/`); English is an override (`values-en/`).
  `supportsRtl="true"`, so layout direction follows the language automatically. Typography uses zero letter-spacing and
  generous line heights, which Arabic script needs.
- **Design tokens only.** Raw colors live in `ui/theme/Color.kt` (internal) and are exposed through
  `MaterialTheme.colorScheme`. Screens contain no hardcoded colors.
- **Honest data.** Statistics are real zeros from a "nothing recorded" state, not sample data. Placeholder cards carry a
  "not implemented yet" badge, a dashed outline and no click handler.
- **Responsive.** Content is capped at 840dp wide and centered; the grid is 2 columns on phones and 4 on tablets (>= 600dp).
- **Room and DataStore are declared in the version catalog but not yet added to `app/build.gradle.kts`.** Phase 1 has
  no persisted data, and Room cannot compile without at least one entity. They will be wired in when the first real
  feature needs them.
- **No permissions, analytics, ads or network libraries.**

## Dependency versions

AGP 8.7.3, Kotlin 2.0.21 (Compose compiler plugin), Compose BOM 2024.12.01 (Material 3), Navigation Compose 2.8.5,
Lifecycle 2.8.7, Activity Compose 1.9.3, Core KTX 1.15.0, Coroutines 1.9.0, JUnit 4.13.2.

## Not yet verified

The Phase 1 code has **not** been compiled or run (see the Phase 1 report). The first step on a machine with the
Android SDK is `./gradlew assembleDebug testDebugUnitTest`.
