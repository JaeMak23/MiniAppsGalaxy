# Module Structure — Clean Architecture + Multi-Module (CMP)

```
settings.gradle.kts
include(":androidApp")
include(":desktopApp")
include(":shared")
// core
include(":core:common")
include(":core:network")
include(":core:data")
include(":core:domain")
include(":core:ui")
include(":core:navigation")
include(":core:di")
// features
include(":feature:auth")
include(":feature:dashboard")
```
- One `include(...)` per line — cleaner diffs, easier to scan than comma-separated lists.

## `core/` — generic, feature-agnostic, reusable across the whole app

```
core/
├── common/      → Result/Resource wrappers, DispatcherProvider, Logger,
│                   base UseCase interface, generic extensions, constants
│                   (no Compose, no Ktor, no DB — depends on nothing else in core)
├── network/     → NetworkClient (Ktor) setup, interceptors, generic ApiError
├── data/        → DB driver factory, DataStore/preferences wrapper, base mappers
│                   (NOT feature-specific repository impls)
├── domain/      → only generic/shared domain contracts (if any), e.g. Pagination
│                   (NOT feature-specific repositories like AuthRepository)
├── ui/
│   ├── theme/       → palette files (one per flavor), theme composition, ThemeManager
│   ├── components/  → all reusable composables (buttons/, textfields/, layout/, adaptive/)
│   ├── icons/       → AppIcons object (wraps Material + custom ImageVectors)
│   ├── models/      → pure UI-state models only (not domain models)
│   └── util/        → ColorUtil, ModifierUtil (UI-only utilities)
├── navigation/  → Route contracts, Navigator interface, NavigationEvent
│                   (shared infra — NOT the AppNavigation()/NavDisplay composable itself)
└── di/          → Koin modules binding core services (CoreModule, NetworkModule)
```

**Dependency direction:** `common` ← everything. `ui`, `network`, `data`, `navigation`,
`domain` depend only on `common`, not on each other. `di` wires everything together.

## `shared/` — app-level composition

```
shared/.../presentation/navigation/
  └── AppNavigation.kt   → NavDisplay + graph assembly (UI composition, belongs
                            in presentation, not core:navigation)
```

## `feature:<name>/` — business-domain-specific, one feature = one module

```
feature/auth/
├── data/
│   └── AuthRepositoryImpl.kt     ← implementation lives with the feature
├── domain/
│   └── AuthRepository.kt         ← interface (contract) also lives with the feature
└── presentation/
    └── ui/
        └── AuthViewModel.kt
```

**Rule of thumb:** if it's tied to a specific business concept (User, Auth, Order,
Dashboard), it belongs in `feature:<name>`, not `core:*`. `core` should be stable
and feature-agnostic — features depend on core, never the reverse.
