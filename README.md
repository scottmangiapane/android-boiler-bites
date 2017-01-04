## Boiler Bites - Beta (functional, but not completed)

Tired of checking each dining court individually to see which has the best food? With BoilerBites, you don't have to. BoilerBites is an app designed to help Purdue University students find their favorite foods on campus effortlessly. Just enter a list of your favorite foods, and BoilerBites will automatically notify you when and where those foods are being served.

## Download

&lt;no current release&gt;

## Screenshots

&lt;no screenshots&gt;

## Build Instructions

* Create a new project in Android Studio  
  Package name: com.cactuslabs.boilerbites
* Replace the contents of `/app/source/main/` with this repo
* Add dependencies to `/app/build.gradle`

## Build Requirements

* Android Studio
* Android Software Development Kit
* Java Development Kit

## Dependencies

In the project's `/app/build.gradle` file, add the following dependencies.
```groovy
dependencies {
    compile 'com.android.support:appcompat-v7:25.1.0'
    compile 'com.android.support:cardview-v7:25.1.0'
    compile 'com.android.support:design:25.1.0'
}
```
