# Itunes Movies - Demo App

Itunes Movies is using Model-View-ViewModel (ie MVVM) template client application architecture with modularised dependencies. Used LiveData to ensures UI matches on data state and Data Binding to display the details.

# Features
 * Display master detail list with (Track Name, Artwork, Price, Genre)
 * Display previously visited date.
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
 * Navigation
 * Retrofit
 * gson
 * Fresco
 * Dagger2
 * Coroutine
 
 <b>Mobile Phone: List Screen and Details Screen:</b><br />
 <img src="https://github.com/eduardodelito/Movies/blob/master/screen/Screenshot_1590060430.png" width="30%" />
 &nbsp;&nbsp;
 <img src="https://github.com/eduardodelito/Movies/blob/master/screen/Screenshot_1590060437.png" width="30%" />
  &nbsp;&nbsp;
  <img src="https://github.com/eduardodelito/Movies/blob/master/screen/Screenshot_1590067450.png" width="30%" />
 <br /><br />
 
 <b>Tablet: List and Details on single screen.</b><br />
 <img src="https://github.com/eduardodelito/Movies/blob/master/screen/Screenshot_1590060465.png" width="40%" />
    &nbsp;&nbsp;
 <img src="https://github.com/eduardodelito/Movies/blob/master/screen/Screenshot_1590067318.png" width="40%" />
   &nbsp;&nbsp;
 <br /><br />
 
 


