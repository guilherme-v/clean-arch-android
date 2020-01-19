# Android Clean Architecture Sample

## Libraries and tools used:

* [Kotlin](https://kotlinlang.org/)
* [RxKotlin](https://github.com/ReactiveX/RxKotlin)
* [Dagger2](https://github.com/google/dagger) (v2.25.4)
* [Retrofit](http://square.github.io/retrofit/)
* [OkHttp](http://square.github.io/okhttp/)
* [Gson](https://github.com/google/gson)
* [Android Jetpack](https://developer.android.com/jetpack)
    * [Livedata](https://developer.android.com/topic/libraries/architecture/livedata)
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    * [Room](https://developer.android.com/topic/libraries/architecture/room)
    * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation.html)
    * ~~[ViewBinding](https://developer.android.com/topic/libraries/view-binding)~~
* [AndroidX Test libraries](https://developer.android.com/training/testing/set-up-project)
* [MockK](https://github.com/mockk/mockk)
* [Espresso](https://developer.android.com/training/testing/espresso/index.html)
* [Robolectric](http://robolectric.org/)

## Overview

![architecture](./art/arch1.png?raw=true)

As described in Uncle Bob's webpage, clean architecture enforces the separations of concerns dividing the software into layers. Each one of these layers focuses on doing one single thing, so it follows the Single Responsibility Principle. They also have their own Model representations and any communication needed with external layers are made through the use of abstractions. 

The architecture also follows the Dependency Rule, which states that an outer layer can depend on an inner layer, but the other way around is not possible. As result, the application becomes highly decoupled, easy to maintain, to test and to adapt.

## Details

![architecture](./art/arch2.png?raw=true)

### MobileUI
The application user interface. It depends on the Android Framework containing classes like Activities, Fragments, Views, Adapters, and others.

### Presentation
This layer will receive data from the Domain's use cases, it will then prepare these to be presented on the UI. In general, it is the recommended place to format/internationalize things. 

### Domain
It represents the domain of the problem we are trying to solve, that is, your business rules. It is the architecture's central layer, therefore, it has no dependencies on external layers and should be a plain kotlin module. It includes just plain objects, use-cases, and data access abstractions.

### Data
It is the layer that implements the data access abstractions defined into the domain. In other words, it is the layer responsible for provide data to those use cases. Since it is capable of fetch/push data from different sources, it also defines more abstractions that will be implemented by others layers.

### Remote
Layer responsible for handle the communication to the network. It has the implementation of the remote abstraction defined into the Data layer.

### Cache
This layer provides access to any kind of local storage: database, shared preferences, files, and others. It implements the abstractions defined into the Data layer.


## About this implementation

Much of the content present in this sample is based on Joe Birch's Caster.IO online lessons, this sample is the result of these lessons. But it includes personal opinions, updates, libraries explorations, and learnings. Some examples are:

Unit tests are using MockK instead of Mockito: 

```kotlin
    @Test
    fun `it returns a list of projects`() {
        // arrange
        val projects = FakeProjectFactory.makeProjectList(2)
        every { projectsRepository.getProjects() } returns Observable.just(projects)

        // act
        val testObserver = getProjects.buildUseCaseObservable().test()

        // assert
        assert(testObserver.values().first() == projects)
    }
```

Integration tests are following the Robot Pattern and using the AndroidX Testing Library:

```kotlin
    @Test
    fun itShowsAListOfProjects() {
        // arrange
        val projects = FakeFactory.makeProjectList(10)
        every { projectsRepository.getProjects() } returns Observable.just(projects)

        // act
        launchFragmentInContainer<BrowseProjectsFragment>()

        // assert
        projects.forEachIndexed { index, project ->
            robot { 
                scrollToViewWithIndex(index) 
            } verify { 
                itemShowText(project.fullName)
            }
        }
    }
```

## Roadmap - What do you want to see next?
* Creates another MobileUI module, but using only Android Compose Library
* Include Kotlin Coroutine and shows how it can play along with RxKotlin
* Pagination
* Android Dark Theme
* ViewBinding or Data binding 
* WorkManager
* MotionLayout
* Moshi
* Ktlint and Detekt
* Flutter?
