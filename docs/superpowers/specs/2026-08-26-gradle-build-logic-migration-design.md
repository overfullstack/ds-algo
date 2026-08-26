# Gradle Build Logic Migration Design

## Objective

Replace the repository's implicit `buildSrc` build with the current Gradle-recommended
`build-logic` included build while preserving the pinned Gradle, Kotlin, JDK, plugin,
and dependency versions and the existing module behavior.

## Current State

The root build contains `common`, `ds-algo`, and `old` subprojects. Shared policy is
implemented by three precompiled Kotlin DSL convention plugins in `buildSrc`:

- `ds-algo.root-conventions`
- `ds-algo.kt-conventions`
- `ds-algo.sub-conventions`

`buildSrc` imports the root version catalog explicitly and places the Gradle plugins
needed by those conventions on its implementation classpath. The root build applies
the root convention and separately declares Kover so that its build script can
configure the Kover extension.

## Architecture

Create a standalone included build at `build-logic` and register it from the root
`settings.gradle.kts` with `pluginManagement { includeBuild("build-logic") }`. The
included build will retain the existing precompiled script plugin IDs, source files,
plugin dependencies, and repository declarations. Its settings will explicitly
import `../gradle/libs.versions.toml`, since version catalogs are not inherited by an
included build.

The module build scripts will continue applying the same convention plugin IDs. This
keeps the migration independent of any future redesign of library, application, or
repository conventions.

Root-only Kover report configuration will move into `ds-algo.root-conventions`. The
root `build.gradle.kts` will then apply only that convention plugin, eliminating the
duplicate implementation-plugin declaration while retaining the same report behavior.

## Files and Scope

The migration will:

- modify `settings.gradle.kts`;
- replace tracked `buildSrc/**` sources with corresponding `build-logic/**` sources;
- simplify `build.gradle.kts` after moving its Kover configuration;
- update `CLAUDE.md` to describe `build-logic`;
- leave module source code and module build scripts unchanged unless verification
  demonstrates that a migration-specific adjustment is required.

The migration will not change dependency versions, plugin versions, the Gradle
wrapper, the JDK toolchain, repository policy, source sets, or module plugin roles.
The existing user-owned `.idea/kotlinc.xml` modification is outside scope and must
remain untouched.

## Failure Handling

Gradle configuration, convention-plugin compilation, or IDE-model failures introduced
by the relocation are migration regressions and must be corrected within this scope.
Failures reproducible on the pre-migration revision will be reported separately and
will not justify unrelated source or dependency changes. Existing third-party Gradle
deprecation warnings will be recorded but not suppressed or refactored without evidence
that repository-owned build logic causes them.

## Verification

Verification is behavior-oriented rather than a test of the directory name:

1. Capture a pre-migration full-build baseline.
2. Build the standalone included build.
3. Run root `projects` and `help` model checks.
4. Run the root `clean build` verification suite.
5. Repeat a configuration-cache-compatible model invocation and confirm reuse.
6. Synchronize IntelliJ's filesystem model and inspect imported modules through the
   IDEA MCP and IntelliJ Index MCP.
7. Run IntelliJ diagnostics on changed Kotlin DSL files.
8. Inspect the final Git diff and status, including confirmation that the existing
   `.idea/kotlinc.xml` change was neither staged nor modified by this work.

Success means the included build compiles, the root build and tests pass with the
same pinned versions, Gradle's project hierarchy remains unchanged, IntelliJ imports
`build-logic` in place of `buildSrc`, and no unrelated files are changed.
