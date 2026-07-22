# CLAUDE.md

Practice repo of DS & algorithm problems, well-commented to show how to approach a
solution. Kotlin-first (~335 files) with a functional-Java subset (~153 files).

## Solution style — read first

**Coding conventions live in `.claude/rules/style.md`** (start-simple policy,
complexity budgets, Java/Kotlin idioms, functional & recursion patterns, exemplar
files). Follow it for any solution you write. This file covers only build/test/layout.

## Build & test

Toolchain versions are centralized in `libs.versions.toml` (JDK, Kotlin, JUnit,
Kotest, …); the JDK is also pinned in `.sdkmanrc`. Java compiles with `--enable-preview`.

```shell
./gradlew clean build                                    # full build
./gradlew [module]:test --tests [fully.qualified.TestName]   # one test
```

Examples:
```shell
./gradlew ds-algo:test --tests leetcode.array.TopKFrequentWordsTest
./gradlew common:test  --tests ga.overfullstack.ds.graph.DiGraphTest
```

- Tests: **JUnit 5** + **Kotest** matchers, with **power-assert** on `shouldBe`.
- Coverage via **Kover** (HTML report on `check`).

## Formatting (Spotless — run before committing)

```shell
./gradlew spotlessApply     # auto-format; spotlessCheck runs in build
```
Kotlin/Kotlin-Gradle → `ktfmt().googleStyle()`. Java → `googleJavaFormat()` with
import ordering, unused-import removal, no wildcard imports. Indent: **tabs**
(`leadingSpacesToTabs(2)`).

## Module layout

- **`common`** — shared data structures & utilities (`ds/`, `utils/`, `testcase/`),
  the universal `TestCases` test framework, and resources. Depends on kotlinx-serialization + revoman.
- **`ds-algo`** — the solutions. Depends on `:common`.
  - `src/main/kotlin/{leetcode,educative,algoexpert,gfg,hackerrank,…}/<topic>/`
  - `src/main/java/practice/<topic>/` — the functional-Java solutions
  - `src/test/kotlin/…` mirrors main; test fixtures in `src/test/resources/`
- **`old`** — legacy Java (`ds/`, `cci/`, `graph/`, `sorting/`, …); depends on both.

## Conventions plugins (`buildSrc/`)

`ds-algo.root-conventions`, `ds-algo.kt-conventions` (Kotlin + power-assert +
kotest), `ds-algo.sub-conventions` (spotless + kover + JUnit + JDK toolchain).
Versions are centralized in `libs.versions.toml`.
