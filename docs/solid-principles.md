# SOLID Principles & Coding Conventions

## SOLID Applied to This Project

| Principle | How it's applied here |
|-----------|------------------------|
| **SRP** | Each `core:*` module has one responsibility (network, data, ui, nav, di). Within `core:ui`, avoid duplicate-purpose dirs (e.g. don't keep both `components/` and `uikit/` — merge). |
| **OCP** | New theme flavor (e.g. "Green") = add new palette/theme files only, no existing file touched. New icon = add to `AppIcons`, no call-site changes. |
| **LSP** | All `AppThemeFlavor` implementations (Blue, Orange) are interchangeable via the same interface — `getColorScheme`/`meshGradient` never care which flavor is active. |
| **ISP** | `AppThemeFlavor` interface exposes only what's needed (`darkColors`, `lightColors`, `darkGradient`, `lightGradient`) — no bloated contract. |
| **DIP** | Features depend on `core:*` abstractions (interfaces), not concrete implementations. `AuthRepository` (interface) and `AuthRepositoryImpl` both live in `feature:auth`, but consumers (ViewModel) depend on the interface. |

## Naming & Code Conventions

- **Backing cache fields:** `_camelCaseName` matching the public property name exactly
  (e.g. public `CustomDarkMode` → backing `_customDarkMode`) — no abbreviated prefixes
  like `_cDarkMode` or `_mDarkMode`.
- **Icon objects:** group dual-state icons (`Outlined`/`Filled`) as nested objects
  (`AppIcons.Home.Filled`) rather than flat names (`HomeFilled`).
- **Custom `ImageVector`s:** prefix `Custom` (not `MaterialIcons`, which misleadingly
  implies they come from the Material icons library when they're hand-copied vector data).
- **Enum vs sealed class:** use `enum class` when variants carry fixed data with no
  differing behavior/shape (e.g. `ThemeManager`); reserve `sealed class` for
  genuinely differently-shaped subtypes.
- **Compose state:** pass primitive values (`Boolean`) into pure utility functions,
  not `MutableState<Boolean>` — unwrap `.value` at the call site inside Composable
  scope, keep utility functions state-framework-agnostic and testable.
