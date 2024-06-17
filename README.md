# Project Features

## Clean Architecture with Use Cases:
- Implement the Clean Architecture pattern, separating your project into layers (presentation, domain, data).
- Define and implement use cases that represent the application's business logic independently of the UI or external dependencies.

## Flow / Coroutines:
- Utilize Kotlin Coroutines for asynchronous programming.
- Use Kotlin Flow to represent and handle streams of data asynchronously.

## Retrofit:
- Integrate Retrofit library to handle network requests and communicate with a RESTful API.
- Define Retrofit service interfaces to specify API endpoints and request methods.

## Navigation Component + NavArgs:
- Utilize Navigation Component to implement navigation between different screens or destinations in your app.
- Pass data between destinations using Safe Args and NavArgs for type-safe argument passing.

## RecyclerView with DiffUtil:
- Implement RecyclerView to display lists of data efficiently.
- Utilize DiffUtil to calculate and efficiently update the contents of RecyclerView when the underlying data changes.

---

## Branches

### master
The `master` branch contains the basic implementation of the Clean Architecture project with API calls and all fundamental features described above.

### error-handle
The `error-handle` branch enhances error handling mechanisms:
- Implements error handling from API calls.
- Optionally integrates Room persistence for caching.
- Parses error JSON responses to extract error messages.
- Applies retry mechanisms:
  - Globally for the entire application using custom configurations in `HttpClient`.
  - Specifically for endpoints or repositories where necessary.
- Introduces detailed logging for improved visibility into error scenarios.

### koin
The `koin` branch replaces Dagger Hilt with Koin for dependency injection:
- Updates the entire application to use Koin for dependency injection instead of Dagger Hilt.
- Ensures all dependencies are correctly managed and injected using Koin's modules and scopes.

![demo](https://github.com/Tonistark92/Task/assets/86676102/0d372a50-c454-448a-aa87-95d0c5d085a0)
