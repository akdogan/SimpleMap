## How to build the app
The app requires a foursquare api key. Put it into the `local.properties` file:
```
foursquare_api_key="your_api_key"
```
__Important:__ The app will crash if there is no api key

## App Structure
The app is structured following the MVVM pattern.

Every screen (there is only one currently) has its own ViewModel.

The ViewModel retrieves data from remote or local via a Repository.

Data models are strictly kept within their respective domain, for example remote models are never used in the viewmodel or in the ui directly.

Compose states that should survive configuration changes are bundled in a state holder class, which itself lives in the viewmodel.

Koin was used for dependency injection.

## External Libraries
- [Osmdroid](https://github.com/osmdroid/osmdroid) Open Source Map SDK
- [Koin](https://insert-koin.io/): Dependency injection
- [Timber](https://github.com/JakeWharton/timber): Logging
- [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization) Serialization of Api response
- [Retrofit](https://square.github.io/retrofit/) Api client
- [OkHttp](https://square.github.io/okhttp/) Already included through retrofit, but the dependency is required for adding interceptors and configuring the serializer
- [Retrofit2-kotlinx-serialization-converter](https://github.com/JakeWharton/retrofit2-kotlinx-serialization-converter) Retrofit does not have built in support for kotlinx serialization (yet)
- [Glide](https://github.com/bumptech/glide) Image Loading
- [Truth](https://github.com/google/truth) For Unit Test Assertions