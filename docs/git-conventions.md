# Git Workflow & Commit Conventions

## 1. Branching Strategy

```
master
 └── develop
      ├── chore/initial-setup          (merged → develop)
      ├── feature/koin-di-setup        (merged → develop)
      ├── feature/navigation3-setup
      ├── feature/core-modules-setup   (core:ui, core:data, core:domain, core:network)
      ├── feature/auth
      ├── feature/dashboard
      └── ...
```

**Rules:**
- `master` — production-ready only.
- `develop` — integration branch; all feature branches merge here.
- One branch per logical unit of work (a setup task, a feature, a core module group).
- Branch off the **latest local `develop`**, after pulling from remote:
  ```
  git checkout develop
  git pull origin develop
  git checkout -b feature/xyz
  ```
- Merge via **GitHub Pull Request** (preferred over Android Studio's Git UI or raw
  terminal merge) — gives review trail, diff view, and history even solo.
- Don't delete a merged branch immediately — safe to leave it; clean up later if wanted.

**Branch naming convention:**
```
chore/<short-description>       → tooling, config, upgrades, cleanup
feature/<short-description>     → new functionality
fix/<short-description>         → bug fixes
refactor/<short-description>    → code restructuring, no behavior change
```
Use kebab-case, no ticket-number prefix unless the project uses issue tracking.

---

## 2. Commit Message Convention (Conventional Commits)

```
<type>: <short summary, imperative mood, lowercase>

- <bullet detail 1>
- <bullet detail 2>
- <bullet detail 3>
```

**Types used in this project:**
| Type       | Use for                                              |
|------------|-------------------------------------------------------|
| `feat`     | New feature or capability                             |
| `fix`      | Bug fix                                                |
| `chore`    | Tooling, deps, build config, non-code maintenance      |
| `refactor` | Code restructuring without behavior change             |
| `docs`     | Documentation only                                     |
| `style`    | Formatting, no logic change                            |
| `test`     | Adding/fixing tests                                    |

**Examples from this project:**
```
chore: initial project setup and dependency updates

- Upgraded Android Gradle Plugin to 9.3.2 and Gradle Wrapper to 9.7.1.
- Updated Android compile SDK to 37 and established JVM 21 as the project-wide target.
- Centralized JVM toolchain configuration in the root build.gradle.kts for all subprojects.
```

```
feat: setup Koin dependency injection across platforms

- Added Koin 4.2.2 dependencies to version catalog.
- Created :core:di module to centralize Koin initialization logic.
- Implemented initKoin and appModule in the shared module for multiplatform DI.
```

**Rules:**
- Summary line: imperative mood ("add", not "added"/"adds"), lowercase after the colon, no period.
- Body bullets: past tense is fine here (describing what was done), one bullet per distinct change.
- Keep summary under ~72 chars.
