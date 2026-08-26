# Gradle Build Logic Migration Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Replace the implicit `buildSrc` build with a Gradle-recommended `build-logic` included build without changing pinned versions or module behavior.

**Architecture:** Register a standalone `build-logic` build through root plugin management and retain the existing precompiled convention-plugin IDs. Encapsulate root-only Kover configuration in the root convention plugin, leaving consumer module build scripts unchanged.

**Tech Stack:** Gradle 9.8.0-milestone-2, Kotlin DSL, Kotlin 2.4.20-RC, JDK 26, Kover, Spotless, IntelliJ IDEA MCP, IntelliJ Index MCP

**Spec:** `docs/superpowers/specs/2026-08-26-gradle-build-logic-migration-design.md`

## Global Constraints

- Preserve every version in `gradle/libs.versions.toml`, `gradle/wrapper/gradle-wrapper.properties`, and `.sdkmanrc`.
- Preserve the plugin IDs `ds-algo.root-conventions`, `ds-algo.kt-conventions`, and `ds-algo.sub-conventions`.
- Do not change module source sets, dependency declarations, repository policy, or plugin roles.
- Treat configuration validation as the approved alternative to an artificial directory-name test.
- Do not modify unrelated files; the previously user-owned `.idea/kotlinc.xml` update is already committed separately as `b71a813c`.
- Use `apply_patch` for content edits and explicit `git mv` commands only for the tracked directory relocation.

---

### Task 1: Establish the baseline and create the included build

**Files:**

- Move: `buildSrc/` to `build-logic/`
- Modify: `build-logic/settings.gradle.kts`
- Modify: `settings.gradle.kts`

**Interfaces:**

- Consumes: root version catalog at `gradle/libs.versions.toml` and the existing convention-plugin source tree.
- Produces: an included plugin build exposing the same three plugin IDs to the root build.

- [ ] **Step 1: Run the pre-migration full-build baseline**

Run:

```bash
./gradlew clean build --warning-mode all --console=plain
```

Expected: exit 0. Record any third-party deprecation warnings. If this baseline fails, diagnose and report it before changing build files.

- [ ] **Step 2: Relocate the tracked build-logic directory**

Run:

```bash
git mv buildSrc build-logic
```

Expected: Git reports renames for the two build files and five Kotlin source files; no generated `buildSrc/build/**` paths are staged.

- [ ] **Step 3: Make the included build settings explicit**

Replace `build-logic/settings.gradle.kts` with:

```kotlin
pluginManagement {
  repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    google()
  }
}

dependencyResolutionManagement {
  versionCatalogs { create("libs") { from(files("../gradle/libs.versions.toml")) } }
}
```

- [ ] **Step 4: Register build-logic for plugin resolution**

Add this block at the beginning of root `settings.gradle.kts`:

```kotlin
pluginManagement { includeBuild("build-logic") }
```

Keep the root project name and all three `include(...)` calls unchanged.

- [ ] **Step 5: Build the standalone convention-plugin build**

Run:

```bash
./gradlew -p build-logic clean build --warning-mode all --console=plain
```

Expected: exit 0; the three precompiled Kotlin DSL plugins compile and plugin descriptors are generated.

- [ ] **Step 6: Verify the root resolves plugins from the included build**

Run:

```bash
./gradlew projects help --warning-mode all --console=plain
```

Expected: exit 0 with root project `ds-algo-root` and exactly `:common`, `:ds-algo`, and `:old` in the project hierarchy.

- [ ] **Step 7: Commit the structural migration**

Run:

```bash
git add settings.gradle.kts build-logic
git diff --cached --check
git commit -m "build: migrate conventions to included build"
```

Expected: the commit contains only the directory relocation plus the two settings-file changes.

### Task 2: Encapsulate root-only Kover configuration

**Files:**

- Modify: `build-logic/src/main/kotlin/ds-algo.root-conventions.gradle.kts`
- Modify: `build.gradle.kts`

**Interfaces:**

- Consumes: the Kover Gradle plugin already applied by `ds-algo.root-conventions`.
- Produces: the same root HTML-on-check report behavior without a duplicate root plugin declaration.

- [ ] **Step 1: Move the Kover report policy into the convention plugin**

Append this configuration to `build-logic/src/main/kotlin/ds-algo.root-conventions.gradle.kts` after the existing `testlogger` block:

```kotlin
kover { reports { total { html { onCheck = true } } } }
```

Remove the unused imports for Spotless line endings and the XML formatter from this convention file. Preserve its group, version, description, repositories, and test logger behavior.

- [ ] **Step 2: Simplify the root build script**

Replace `build.gradle.kts` with:

```kotlin
plugins { id("ds-algo.root-conventions") }
```

- [ ] **Step 3: Compile and exercise the relocated configuration**

Run:

```bash
./gradlew clean help projects --warning-mode all --console=plain
```

Expected: exit 0, convention plugins compile from `build-logic`, and the project hierarchy remains unchanged.

- [ ] **Step 4: Prove configuration-cache reuse**

Run the same model invocation again without `clean`:

```bash
./gradlew help projects --warning-mode all --console=plain
```

Expected: exit 0 and output contains `Reusing configuration cache.`

- [ ] **Step 5: Commit the root-policy encapsulation**

Run:

```bash
git add build.gradle.kts build-logic/src/main/kotlin/ds-algo.root-conventions.gradle.kts
git diff --cached --check
git commit -m "build: encapsulate root coverage convention"
```

Expected: the commit contains exactly the root build script and root convention plugin.

### Task 3: Update documentation and perform full verification

**Files:**

- Modify: `CLAUDE.md`

**Interfaces:**

- Consumes: the completed included-build layout and unchanged public convention-plugin IDs.
- Produces: contributor guidance matching the repository layout and final evidence that Gradle and IntelliJ accept the migration.

- [ ] **Step 1: Update contributor documentation**

Change the heading:

```markdown
## Conventions plugins (`build-logic/`)
```

Keep the three plugin names and their descriptions unchanged. Add one sentence stating that root `settings.gradle.kts` includes this standalone plugin build through `pluginManagement`.

- [ ] **Step 2: Run the complete Gradle verification**

Run:

```bash
./gradlew clean build --warning-mode all --console=plain
```

Expected: exit 0 with all compilation, test, Spotless, and Kover tasks successful. Compare warnings with the Task 1 baseline and investigate any new repository-owned warning.

- [ ] **Step 3: Synchronize the IntelliJ index**

Call IntelliJ Index MCP `ide_sync_files` with:

```json
{
  "project_path": "/Users/gopala.akshintala/code-clones/my-github/ds-algo",
  "paths": ["settings.gradle.kts", "build.gradle.kts", "build-logic", "CLAUDE.md"]
}
```

Expected: synchronization succeeds without modifying source files.

- [ ] **Step 4: Inspect IDE modules through IDEA MCP**

Call IDEA MCP with project path `/Users/gopala.akshintala/code-clones/my-github/ds-algo` and command:

```text
get_project_modules
```

Expected: project modules include `build-logic` in place of `buildSrc`; `common`, `ds-algo`, and `old` remain present. If the live IDE model remains stale after `ide_sync_files`, record that limitation explicitly and use the successful Gradle included-build model as the authoritative result; do not restart the user's IDE.

- [ ] **Step 5: Run IntelliJ diagnostics on changed Kotlin DSL files**

Call IntelliJ Index MCP `ide_diagnostics` separately for:

```text
settings.gradle.kts
build.gradle.kts
build-logic/settings.gradle.kts
build-logic/build.gradle.kts
build-logic/src/main/kotlin/ds-algo.root-conventions.gradle.kts
build-logic/src/main/kotlin/ds-algo.kt-conventions.gradle.kts
build-logic/src/main/kotlin/ds-algo.sub-conventions.gradle.kts
```

Use severity `all` and the absolute project path above. Expected: no errors. Record the existing incubating `JvmTestSuite` warnings separately if IntelliJ still reports them.

- [ ] **Step 6: Inspect final scope and repository state**

Run:

```bash
git diff --check
git status --short
git diff --stat HEAD~2..HEAD
```

Expected before the documentation commit: only `CLAUDE.md` is uncommitted, no generated build outputs are tracked, and no dependency or wrapper versions changed.

- [ ] **Step 7: Commit the documentation update**

Run:

```bash
git add CLAUDE.md
git diff --cached --check
git commit -m "docs: describe included build logic"
```

Expected: the commit contains only `CLAUDE.md`.

- [ ] **Step 8: Run the final clean-tree verification**

Run:

```bash
./gradlew build --warning-mode all --console=plain
git status --short --branch
```

Expected: Gradle exits 0 and Git reports a clean worktree with the branch ahead only by the approved local commits.
