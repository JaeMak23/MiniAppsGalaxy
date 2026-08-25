core/
├── common/
│   └── src/commonMain/kotlin/.../core/common/
│       ├── result/
│       │   └── Resource.kt          (Loading/Success/Error wrapper)
│       ├── dispatcher/
│       │   └── DispatcherProvider.kt
│       ├── logger/
│       │   └── Logger.kt
│       ├── usecase/
│       │   └── UseCase.kt           (base interface)
│       ├── extensions/
│       │   ├── StringExt.kt
│       │   ├── DateExt.kt
│       │   └── CollectionExt.kt
│       ├── util/
│       │   └── DebugUtil.kt
│       └── constants/
│           └── AppConstants.kt
│
├── network/
│   └── src/commonMain/kotlin/.../core/network/
│       ├── client/
│       │   └── NetworkClient.kt     (Ktor setup)
│       ├── interceptor/
│       │   └── AuthInterceptor.kt
│       ├── model/
│       │   └── ApiError.kt
│       └── util/
│           └── NetworkUtil.kt
│
├── data/
│   └── src/commonMain/kotlin/.../core/data/
│       ├── local/
│       │   └── DatabaseDriverFactory.kt   (SQLDelight/Room setup)
│       ├── preferences/
│       │   └── PreferencesDataSource.kt   (DataStore wrapper)
│       └── mapper/
│           └── BaseMapper.kt
│
├── domain/
│   └── src/commonMain/kotlin/.../core/domain/
│       ├── model/
│       │   └── (generic domain types, if any — e.g. Pagination)
│       └── repository/
│           └── (generic repository contracts, if any)
│
├── ui/
│   └── src/commonMain/kotlin/.../core/ui/
│       ├── theme/
│       │   ├── Color.kt
│       │   ├── Typography.kt
│       │   ├── Shape.kt
│       │   ├── ThemeBar.kt
│       │   └── AppTheme.kt
│       ├── components/
│       │   ├── buttons/
│       │   ├── textfields/
│       │   ├── layout/
│       │   ├── adaptive/
│       │   └── ShadowContainer.kt
│       ├── icons/
│       │   └── AppIcons.kt
│       ├── models/
│       │   └── (pure UI-state models only)
│       └── util/
│           ├── ColorUtil.kt
│           └── ModifierUtil.kt
│
├── navigation/
│   └── src/commonMain/kotlin/.../core/navigation/
│       ├── Route.kt                 (sealed route contracts)
│       ├── Navigator.kt             (interface)
│       └── NavigationEvent.kt
│
└── di/
└── src/commonMain/kotlin/.../core/di/
├── CoreModule.kt            (binds DispatcherProvider, Logger, etc.)
└── NetworkModule.kt         (binds NetworkClient, etc.)

## Dependency direction: 
- common ← everything; ui, network, data, navigation depend on common only (not on each other); 
- domain depends on common only; 
- di wires them all together and is depended on by androidApp/desktopApp/shared