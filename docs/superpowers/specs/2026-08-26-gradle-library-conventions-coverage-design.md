# Gradle Library Conventions and Coverage Design

## Objective

Replace the repository's order-dependent JVM/Kotlin convention pair with composed
library conventions and make the root Kover report aggregate real coverage from all
three modules. Preserve every pinned Gradle, Kotlin, JDK, dependency, and plugin
version.

## Current State

`common`, `ds-algo`, and `old` all apply `ds-algo.sub-conventions`, which applies the
`application` plugin. Every module therefore exposes `run`, start-script, installation,
and distribution tasks even though no module declares an application `mainClass`.

Kotlin modules must apply both `ds-algo.kt-conventions` and
`ds-algo.sub-conventions`. The two plugins independently own related JVM configuration,
so correct behavior depends on consumers knowing the required combination.

The root and all modules apply the standard Kover plugin, but the root declares no
Kover project dependencies. Its generated HTML report currently states that no
coverage information was found. Test Logger is configured only on the root project,
whose Java test suite has no tests. Java preview is enabled for compilation and
`JavaExec`, but not for `Test` tasks.

## Architecture

Create two composed precompiled Kotlin DSL convention plugins:

- `ds-algo.jvm-library-conventions` applies `java-library`, Kover, Spotless, and Test
  Logger. It owns the Java toolchain, preview flags for `JavaCompile`, `Test`, and
  `JavaExec`, JUnit platform configuration, formatting, and test logging.
- `ds-algo.kotlin-library-conventions` applies the JVM library convention and then
  applies Kotlin/JVM and power-assert. It owns Kotlin toolchain/compiler policy and
  Kotest dependencies.

`common` and `ds-algo` will apply only the Kotlin library convention plus their
existing serialization plugin. `old` will apply only the JVM library convention.
The obsolete `ds-algo.kt-conventions` and `ds-algo.sub-conventions` plugin sources
will be removed.

The root convention will retain the standard Kover plugin and explicitly add Kover
project dependencies for `:common`, `:ds-algo`, and `:old`. This uses Kover's supported
multi-module merge model while keeping the existing catalog-managed Kover version.
The root HTML-on-check policy remains unchanged.

The root `alias(libs.plugins.spotless) apply false` declaration and its upstream issue
comment remain unchanged because focused RED/GREEN evidence from the preceding
`build-logic` migration established that it is required for Spotless/ktfmt classloader
compatibility.

## Behavior and Interfaces

All three modules become JVM libraries. Application-only tasks such as `run`,
`startScripts`, `installDist`, `distTar`, and `distZip` must disappear. Existing source
sets, dependencies, test selection, Kotlin compiler flags, formatting rules, coverage
instrumentation, and module relationships remain unchanged.

Java compilation, Java execution, and test JVMs receive `--enable-preview`. Kotlin/JVM
target validation will remain unchanged in this task; fail-closed target alignment is
reserved for the later hardening follow-up.

Test Logger configuration moves unchanged from the root convention to the JVM library
convention so it configures the projects that own test tasks. The root no longer applies
Test Logger. Its existing Java plugin remains in this task to minimize change around
Kover's merging project; removing empty root lifecycle tasks is a separate cleanup.

## Files and Scope

The implementation will:

- create `build-logic/src/main/kotlin/ds-algo.jvm-library-conventions.gradle.kts`;
- create `build-logic/src/main/kotlin/ds-algo.kotlin-library-conventions.gradle.kts`;
- remove the two superseded consumer convention scripts;
- update `ds-algo.root-conventions.gradle.kts` for explicit Kover aggregation and
  removal of root-only Test Logger configuration;
- update the three module build scripts to apply their single library convention;
- update `CLAUDE.md` to describe the new plugin structure.

It will not change version catalogs, wrapper or toolchain versions, dependency
declarations, repositories, source code, line-ending policy, JVM target validation,
the Spotless compatibility declaration, or unrelated Gradle properties.

## Failure Handling

A missing application task or populated Kover report is a required behavior change;
compilation, test, formatting, or coverage regressions are not. Any pre-existing
failure must be reproduced on the starting commit before widening the scope. Kover
aggregation must not be considered successful merely because an HTML file exists: the
report must contain class coverage and must not contain the no-coverage message.

If applying `java-library` exposes dependency visibility errors, fix only declarations
whose existing consumers demonstrate that they form part of a module's public API.
Do not promote every `implementation` dependency to `api` speculatively.

## Verification

Configuration behavior will be tested through observable Gradle models rather than an
artificial test of plugin filenames:

1. Capture RED evidence that each module exposes application-only tasks and the root
   Kover HTML report says no coverage information was found.
2. Build the standalone `build-logic` included build.
3. Confirm application-only tasks are absent from all three module task models.
4. Run the root Kover HTML report and confirm it contains real class coverage from the
   modules and does not contain the no-coverage message.
5. Confirm test tasks receive `--enable-preview` and Test Logger configuration through
   the JVM library convention.
6. Run the full `./gradlew --quiet clean build` gate.
7. Repeat a configuration-cache-compatible quiet invocation.
8. Synchronize IntelliJ and run diagnostics on changed Kotlin DSL files when the live
   IDE model can see the isolated worktree; otherwise record the exact limitation and
   use the successful Gradle model as authoritative.
9. Inspect Git diff and status to prove versions, repositories, source code, and the
   Spotless compatibility declaration are unchanged.

Every `./gradlew` invocation must include `--quiet` and must not use `--info` or
`--debug`.
