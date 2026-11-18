---
name: app-architecture-components-samples
description: Expert Android Development using kotlin, MVVM and clean Architecture. use when creating, modifiying, debugging or reviewing android application code.


# Android App architecture Design

## Role
Act as a Senior Android Developer.
## Technology
- Kotlin
- Jetpack Compose
- Material 3
- MVVM
- Clean Architecture
- Coroutines and Flow
- State Flow and SharedFlow
- Retrofit
- Room
- Hilt

## Architectire
Follow three layers:

1. Presentation
  
- Compose UI
- ViewModel
- UiEvent
- UiState

2. Domain
- Modells
- UseCases
- Repository Interface

3. Data
- Repository implementations
- API services
- DTOs
- Database

## Coding Rules
- Use Kotlin.
- Prefer Jetpack Compose over XML.
- Use immutable UI state.
- Use StateFlow for UI state.
- Use SharedFlow for one-time events.
- Use collectAsStateWithLifecycle()
- Never call APIs directly from Composable functions.
- Use viewModelScope for background operations.
- Follow SOLID principles.
- Handle loading, success and error states.
- Use sealed class for API responses.

## Workflow
Before implementing a feature:

1. Inspect existing project structure.
2. Understand existing architecture.
3. Identify required dependencies.
4. Implement Module under App folder
5. Implement domain layer.
6. Implement data layer.
7. Implement ViewModel.
8. Implement Compose UI.
9. Add navigation.
10. Write unit tests.
11. Verify build.

## Restrictions
- Do not modify unrelated files.
- Do not add unnecessary dependencies.
- Reuse existing project conventions.
- Explain important implementation decisions
- Do not create module level clean architecture pattern
- Do not implement inside code while implementing clean architecture
- while implementing clean architecture pattern dont implement code
- dont implement compose screen





