# Itunes Movies - Demo App

Itunes Movies is using Model-View-ViewModel (ie MVVM) template client application architecture with modularised dependencies. Used LiveData to ensures UI matches on data state and Data Binding to display the details.
This is based on the Guide to app architecture article with the androidx package, Kotlin 1.3 and production ready coroutine. I use several android architecture components like LiveData, ViewModel ,Room. Here are several libraries that i use:

# Features
 * Display list with (Track Name, Artwork, Price, Genre)
 * Display previously visited date.
 * Display message in the center if the list of movies is empty.
 * Display last recent search.
 * Swipe screen to refresh list.
 * Details with  (Track Name, Artwork, Artist, Genre, Price, Release Date, Length, Description)
 * Used loader in the placeholder image.
 * Error banner for the search query if it is not available.
 
# Dependency modules
 * buildSrc - Initialize all dependency libraries and versions.
 * movies-client - API request and parsing response and save it into local database.
 * movies-common - shared classes and functions.
 * movies-database - database to save data response.
 * movies-ui - To display API response/database into UI.


# Major libraries
 * Navigation - To navigate screen
 * Material - For Android-oriented design
 * LiveData - For lifecycle-aware
 * ViewModel - To outlive specific instantiations of views or LifecycleOwners 
 * Room - database storage
 * Retrofit - Networking
 * Fresco - Image Loader
 * Dagger2 - For injection
 * Coroutine - For asynchronous
 * Mockito - Unit Test
 * Espresso - Unit Test
 
 Used MVVM because it is an alternative to MVC and MVP patterns when using Data Binding technology. The ViewModel has Built in LifeCycleOwner and doesn't have a reference for View.
 
 <b>Mobile Phone: List Screen and Details Screen:</b><br />
  <img src="https://github.com/eduardodelito/Movies/blob/master/screen/Screenshot_1590131761.png" width="20%" />
  &nbsp;&nbsp;
 <img src="https://github.com/eduardodelito/Movies/blob/master/screen/Screenshot_1590060430.png" width="20%" />
 &nbsp;&nbsp;
 <img src="https://github.com/eduardodelito/Movies/blob/master/screen/Screenshot_1590060437.png" width="20%" />
  &nbsp;&nbsp;
  <img src="https://github.com/eduardodelito/Movies/blob/master/screen/Screenshot_1590067450.png" width="20%" />
 <br /><br />
 
 <b>Tablet: List and Details on single screen.</b><br />
  <img src="https://github.com/eduardodelito/Movies/blob/master/screen/Screenshot_1590131642.png" width="30%" />
     &nbsp;&nbsp;
 <img src="https://github.com/eduardodelito/Movies/blob/master/screen/Screenshot_1590060465.png" width="30%" />
    &nbsp;&nbsp;
 <img src="https://github.com/eduardodelito/Movies/blob/master/screen/Screenshot_1590067318.png" width="30%" />
   &nbsp;&nbsp;
 <br /><br />
 
 


