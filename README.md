# Android MVVM Base Architecture Application using Android Jetpack Components

# Highlights

1. MVVM Architectural pattern
2. Offline Support
3. Unit test demonstration using JUnit and Mockito
4. UI unit test demonstration using Espresso
5. Gradle scripts for running sonarqube static code analysis, code coverage, etc.

The application has been built with **offline support**. It has been designed using **Android
Architecture components** with **Room** for offline data caching. The application is built in such a
way that whenvever there is a service call, the result will be stored in local database.

The whole application is built based on the MVVM architectural pattern.

# Application Architecture

![alt text](https://cdn2.scalablepath.com/_next/image?url=https%3A%2F%2Fcdn-blog.scalablepath.com%2Fuploads%2F2021%2F12%2Fmvvm-reactive-architecture-1024x937.png&w=1200&q=75)

The main advatage of using MVVM, there is no two way dependency between ViewModel and Model unlike
MVP. Here the view can observe the datachanges in the viewmodel as we are using LiveData which is
lifecycle aware. The viewmodel to view communication is achieved through observer pattern (basically
observing the state changes of the data in the viewmodel).

# Screenshots

<img src="/screenshots/Screenshot_main_screen.png" width="346" height="615" alt="Home"/> 
<img src="/screenshots/Screenshot_search.png" width="346" height="615" alt="Home"/>
<img src="/screenshots/Screenshot_article_details.png" width="346" height="615" alt="Details"/>

# Programming Practices Followed

a) Android Architectural Components <br/>
b) Hilt for Dependency Injection <br/>
c) MVVM <br/>
d) Retrofit with Okhttp <br/>
e) Room for data caching <br/>
f) JUnit for Unit testing <br/>
d) Repository pattern <br/>

# How to build ?

Open terminal and type the below command to generate debug build <br/>

```  gradlew assembleDebug ```

Open terminal and type the below command to generate release build <br/>

```  gradlew assembleRelease ```


# How to generate code coverage report ?

Open terminal and type the following command

``` gradlew clean jacocoTestReport```

The coverage report will be generated on the following path.

``` app/build/reports ```
