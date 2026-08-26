# Gradle Library Conventions and Coverage Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Give each module one composed library convention and make the root Kover report aggregate real coverage from `common`, `ds-algo`, and `old`.

**Architecture:** A JVM library convention owns Java, tests, formatting, coverage instrumentation, and test logging. A Kotlin library convention composes it and adds Kotlin-specific policy; the root retains its report policy and explicitly declares Kover project dependencies.

**Tech Stack:** Gradle 9.8.0-milestone-2, Kotlin DSL, Kotlin 2.4.20-RC, JDK 26, Kover 0.9.9, Spotless 8.10.0, Test Logger 4.0.0, JUnit 5.11.3, Kotest 6.2.4

**Spec:** `docs/superpowers/specs/2026-08-26-gradle-library-conventions-coverage-design.md`

## Global Constraints

- Preserve every version in `gradle/libs.versions.toml`, `gradle/wrapper/gradle-wrapper.properties`, and `.sdkmanrc`.
- Every `./gradlew` invocation must include `--quiet` and must not include `--info` or `--debug`.
- Preserve source sets, dependency declarations, repositories, line-ending policy, JVM target validation, and module relationships.
- Keep root `alias(libs.plugins.spotless) apply false` and its Spotless issue 2646 comment unchanged.
- Do not edit Java or Kotlin application source files.
- Treat observable Gradle task/plugin/report behavior as the configuration test; do not add a production test that asserts plugin filenames.
- Restrict this plan to composed library conventions, preview-test support, Test Logger placement, Kover aggregation, and matching documentation.

---

### Task 1: Replace order-dependent consumer conventions with composed library conventions

**Files:**

- Rename: `build-logic/src/main/kotlin/ds-algo.sub-conventions.gradle.kts` to `build-logic/src/main/kotlin/ds-algo.jvm-library-conventions.gradle.kts`
- Rename: `build-logic/src/main/kotlin/ds-algo.kt-conventions.gradle.kts` to `build-logic/src/main/kotlin/ds-algo.kotlin-library-conventions.gradle.kts`
- Modify: `common/build.gradle.kts`
- Modify: `ds-algo/build.gradle.kts`
- Modify: `old/build.gradle.kts`
- Test temporarily: `.superpowers/gradle-library-conventions.init.gradle`

**Interfaces:**

- Consumes: existing JDK, JUnit, Kotest, Spotless, Kover, power-assert, and Test Logger catalog entries.
- Produces: plugin IDs `ds-algo.jvm-library-conventions` and `ds-algo.kotlin-library-conventions`; all three modules expose library behavior without application distributions.

- [ ] **Step 1: Capture RED application-task evidence**

Run this loop from the repository root:

```bash
for module in common ds-algo old; do
  ./gradlew --quiet ":${module}:tasks" --all |
    rg '^(run|startScripts|installDist|distTar|distZip)\b'
done
```

Expected RED: every module prints `run`, `startScripts`, `installDist`, `distTar`, and `distZip` because the current convention applies `application`.

- [ ] **Step 2: Capture the full-build baseline**

Run:

```bash
./gradlew --quiet clean build --warning-mode all --console=plain
```

Expected: exit 0. Record warnings separately; do not broaden scope for a warning reproducible at the starting commit.

- [ ] **Step 3: Rename the consumer convention sources**

Run:

```bash
git mv build-logic/src/main/kotlin/ds-algo.sub-conventions.gradle.kts build-logic/src/main/kotlin/ds-algo.jvm-library-conventions.gradle.kts
git mv build-logic/src/main/kotlin/ds-algo.kt-conventions.gradle.kts build-logic/src/main/kotlin/ds-algo.kotlin-library-conventions.gradle.kts
```

- [ ] **Step 4: Implement the JVM library convention**

In `ds-algo.jvm-library-conventions.gradle.kts`:

- replace `application` with `` `java-library` ``;
- add `id("com.adarshr.test-logger")` to the plugin block;
- add `import com.adarshr.gradle.testlogger.theme.ThemeType.MOCHA_PARALLEL`;
- add `import org.gradle.api.tasks.testing.Test`;
- add this task configuration after the existing `JavaExec` configuration:

```kotlin
tasks.withType<Test>().configureEach { jvmArgs("--enable-preview") }
```

- add the root convention's existing Test Logger policy unchanged:

```kotlin
testlogger {
  theme = MOCHA_PARALLEL
  showCauses = false
  showSimpleNames = true
}
```

Preserve repositories, Java toolchain, Java compiler/exec preview flags, Spotless targets and formatters, Kover application, JUnit suite selection, and `PLATFORM_NATIVE` line endings exactly.

- [ ] **Step 5: Compose the Kotlin library convention**

In `ds-algo.kotlin-library-conventions.gradle.kts`, make the plugin block:

```kotlin
plugins {
  id("ds-algo.jvm-library-conventions")
  kotlin("jvm")
  kotlin("plugin.power-assert")
}
```

Preserve the catalog lookup, Kotest bundle, Kotlin toolchain, compiler flags,
power-assert configuration, and `JvmTargetValidationMode.WARNING` exactly.

- [ ] **Step 6: Give each module one library convention**

Use these plugin blocks:

`common/build.gradle.kts` and `ds-algo/build.gradle.kts`:

```kotlin
plugins {
  id("ds-algo.kotlin-library-conventions")
  alias(libs.plugins.kotlinx.serialization)
}
```

`old/build.gradle.kts`:

```kotlin
plugins { id("ds-algo.jvm-library-conventions") }
```

Leave all three dependency blocks unchanged.

- [ ] **Step 7: Build the standalone convention build**

Run:

```bash
./gradlew --quiet -p build-logic clean build --warning-mode all --console=plain
```

Expected: exit 0 and descriptors are generated for the two new plugin IDs; descriptors for the two obsolete IDs are absent.

- [ ] **Step 8: Verify library task models are GREEN**

Run:

```bash
for module in common ds-algo old; do
  tasks=$(./gradlew --quiet ":${module}:tasks" --all)
  if printf '%s\n' "$tasks" | rg -q '^(run|startScripts|installDist|distTar|distZip)\b'; then
    printf 'unexpected application task in %s\n' "$module" >&2
    exit 1
  fi
done
```

Expected GREEN: exit 0 with no application-task match.

- [ ] **Step 9: Verify plugin placement and preview-test arguments**

Create `.superpowers/gradle-library-conventions.init.gradle` with:

```groovy
import org.gradle.api.tasks.testing.Test

gradle.projectsEvaluated {
  def buildRoot = gradle.rootProject
  buildRoot.tasks.register("verifyLibraryConventions") {
    doLast {
      ["common", "ds-algo", "old"].each { name ->
        def candidate = buildRoot.project(":${name}")
        assert candidate.pluginManager.hasPlugin("java-library")
        assert !candidate.pluginManager.hasPlugin("application")
        assert candidate.pluginManager.hasPlugin("com.adarshr.test-logger")
        assert candidate.tasks.named("test", Test).get().jvmArgs.contains("--enable-preview")
      }
      assert buildRoot.project(":common").pluginManager.hasPlugin("org.jetbrains.kotlin.jvm")
      assert buildRoot.project(":ds-algo").pluginManager.hasPlugin("org.jetbrains.kotlin.jvm")
      assert !buildRoot.project(":old").pluginManager.hasPlugin("org.jetbrains.kotlin.jvm")
    }
  }
}
```

Run:

```bash
./gradlew --quiet verifyLibraryConventions --init-script .superpowers/gradle-library-conventions.init.gradle
```

Expected: exit 0. Delete only this temporary init script after the assertion passes.

- [ ] **Step 10: Run the composed-convention build gate**

Run:

```bash
./gradlew --quiet clean build --warning-mode all --console=plain
```

Expected: exit 0 with no new repository-owned warning.

- [ ] **Step 11: Commit the composed library conventions**

Run:

```bash
git add build-logic/src/main/kotlin common/build.gradle.kts ds-algo/build.gradle.kts old/build.gradle.kts
git diff --cached --check
git commit -m "build: compose JVM library conventions"
```

Expected: one commit containing only the two convention renames/edits and three module plugin-block edits.

### Task 2: Aggregate Kover coverage and document the convention model

**Files:**

- Modify: `build-logic/src/main/kotlin/ds-algo.root-conventions.gradle.kts`
- Modify: `CLAUDE.md`
- Test output: `build/reports/kover/html/**`

**Interfaces:**

- Consumes: Kover instrumentation applied by `ds-algo.jvm-library-conventions` to all three modules.
- Produces: a root Kover report containing module classes and contributor documentation naming the two new plugin IDs.

- [ ] **Step 1: Confirm the root coverage report is RED**

Run:

```bash
./gradlew --quiet koverHtmlReport
rg -n "No coverage information was found" build/reports/kover/html/index.html
```

Expected RED: `rg` finds the no-coverage message in the root report.

- [ ] **Step 2: Move root Test Logger ownership out and declare Kover inputs**

In `ds-algo.root-conventions.gradle.kts`:

- remove the `MOCHA_PARALLEL` import;
- remove `id("com.adarshr.test-logger")`;
- remove the entire `testlogger` block;
- add these project dependencies after the repository block:

```kotlin
dependencies {
  kover(project(":common"))
  kover(project(":ds-algo"))
  kover(project(":old"))
}
```

Preserve `java`, Kover, coordinates, description, repositories, and the existing HTML-on-check report policy.

- [ ] **Step 3: Build the convention plugin containing Kover accessors**

Run:

```bash
./gradlew --quiet -p build-logic clean build --warning-mode all --console=plain
```

Expected: exit 0; the `kover(...)` dependency accessor compiles in the root convention plugin.

- [ ] **Step 4: Generate and inspect the aggregated report**

Run:

```bash
./gradlew --quiet clean koverHtmlReport
```

Then verify:

```bash
if rg -q "No coverage information was found" build/reports/kover/html/index.html; then
  printf 'root Kover report is still empty\n' >&2
  exit 1
fi

report_pages=$(rg --files build/reports/kover/html | rg '\.html$' | wc -l | tr -d ' ')
test "$report_pages" -gt 1
rg -n "Overall Coverage Summary" build/reports/kover/html/index.html
```

Expected GREEN: no no-coverage message, more than one HTML page, and the root summary heading remains present.

- [ ] **Step 5: Update contributor documentation**

Replace the convention-plugin paragraph in `CLAUDE.md` so it names:

- `ds-algo.root-conventions` for root metadata and aggregated Kover reporting;
- `ds-algo.jvm-library-conventions` for Java libraries, toolchain, preview, JUnit,
  Spotless, Kover instrumentation, and Test Logger;
- `ds-algo.kotlin-library-conventions` as the composed Kotlin/power-assert/Kotest
  extension of the JVM library convention.

Keep the statement that root `settings.gradle.kts` includes `build-logic` through
`pluginManagement`.

- [ ] **Step 6: Run full and configuration-cache gates**

Run:

```bash
./gradlew --quiet clean build --warning-mode all --console=plain
./gradlew --quiet help projects --warning-mode all --console=plain
./gradlew --quiet help projects --warning-mode all --console=plain
```

Expected: all three commands exit 0; the repeated model invocation remains configuration-cache compatible.

- [ ] **Step 7: Synchronize and diagnose through IntelliJ**

Call IntelliJ Index MCP `ide_sync_files` for:

```text
build-logic/src/main/kotlin
common/build.gradle.kts
ds-algo/build.gradle.kts
old/build.gradle.kts
CLAUDE.md
```

Run `ide_diagnostics` with severity `all` on the root convention, both new convention
plugins, and all three module build scripts. Expected: no errors. If the live IDE owns
another checkout or retains a stale Gradle model, record exact MCP evidence and use the
successful standalone/root Gradle builds as authoritative; do not restart the IDE.

- [ ] **Step 8: Inspect final scope**

Run:

```bash
git diff --check
git status --short
git diff -- gradle/libs.versions.toml gradle/wrapper/gradle-wrapper.properties .sdkmanrc build.gradle.kts settings.gradle.kts gradle.properties
```

Expected: only `CLAUDE.md` and the root convention remain uncommitted after Task 1;
the protected files have no diff, including the root Spotless compatibility declaration.

- [ ] **Step 9: Commit coverage aggregation and documentation**

Run:

```bash
git add build-logic/src/main/kotlin/ds-algo.root-conventions.gradle.kts CLAUDE.md
git diff --cached --check
git commit -m "build: aggregate module coverage"
```

Expected: one commit containing only the root convention and documentation.

- [ ] **Step 10: Run final clean-tree verification**

Run:

```bash
./gradlew --quiet clean build --warning-mode all --console=plain
git status --short --branch
```

Expected: Gradle exits 0 and Git reports a clean worktree with only local commits ahead of the base branch.
