# Unscramble App
## Description
This is an app I first built by following the [google codelabs](https://developer.android.com/courses/pathways/android-basics-kotlin-unit-3-pathway-3). I then extended the app's functionality by adding a highscore feature, and I also fixed bugs that still existed after I completed the codelabs.

Unscramble is a single player game app that displays scrambled words. To play the game, the player has to make a word using all the letters from the displayed scrambled word. The player gains 20 points for each word unscrambled correctly. If the player can't unscramble the word correctly they have the option to skip the word and receive a different scrambled word. The player is also able to view their highscore in the game.

Just for some background, the highscore functionality was added after I showed my brother the cool app I made after completing the codelab. After my brother played around with the app, he suggested that it would be nice to have a high score feature.

## Android Components/Features Used
* AndroidViewModel
* Navigation Component
* SafeArgs
* Fragments
* Activity
* LiveData
* Data Binding
* DialogFragment
* MenuProvider
* SharedPreferences

## High Overview of the Structure
The android architectural principle I'm using is driving the UI from a model.
### FinalScoreDialogFragment.kt
* Responsible for displaying and handling the dialog whenever its requested to do so, for example, when the player finishes the game.
### GameFragment.kt
* Responsible for displaying the main UI of the game. It displays the scrambled word, the number of words to unscramble, the score, the input box, the skip and submit buttons, etc. 
* Responsible for making the calls to show the dialog and highscore.
* Responsible for setting up the menu
### GameHighScoreFragment.kt
* Responsible for showing the highscore
### GameViewModel.kt
* Stores and prepares all the data of this app
### ListofWords.kt
* Contains a list of the unscrambled words to be used in the game and also some constants
### MainActivity.kt
* Hosts the fragments required for this app
* Sets up the action bar to work nicely with the navigation between fragments

## Features Added
### Player Can View Their Highscore
To implement this feature I added another fragment to the app, GameHighScoreFragment. The sole purpose of this fragment is to show the highscore of the player. The GameHighScoreFragment gets access to the shared view model managed by delegate class activityViewModels. Using activityViewModels allows the view model used in the app to be the same for all fragments in the app because it's scoped to the activity. 

Adding the GameHighScoreFragment required three main changes. First, it required the need to navigate between fragments, so I used the Navigation Component to navigate from the GameFragment to the GameHighScoreFragment. Secondly, it required modifying the GameViewModel because I needed a way to save the highscore data locally while also having the view model be aware of the highscore data. The change I made in GameViewModel was switching from a ViewModel to an AndroidViewModel. An AndroidViewModel is Application context aware which is what I needed in order to pass the Applicaton context to the sharedPreferences and save the high score data locally. Lastly, adding the GameHighScoreFragment lead me to create a menu because I wanted to add an item in the app bar that a player could select in order to see their high score. Since OnCreateMenuOptions was recently deprecated I made a menu using MenuHost and MenuProvider. By using MenuHost and MenuProvider I was able to add an item in the appbar that shows the highscore when clicked.

## Bugs Fixed
### Dialog Disappearing on Device Rotation
To fix this bug where the dialog "disappeared" when the device was rotated I used a DialogFragment. In the DialogFragment I also made it so that the player can't swipe back or touch outside of the dialog to dismiss it. Putting this restriction helps avoid players getting an infinite amount of points by just dismissing the dialog and continuing to play.

This DialogFragment also contains a reference to the activity scoped AndroidViewModel in order to display the score of the player at the end of the game and also to call a function which reinitializes the data inside the AndroidViewModel
### Correct Player Input With Whitespace After it is Considered Incorrect
To fix this issue I simply used the Kotlin trimEnd function to remove the trailing whitespace of the player's input. I bumped into this issue whenever I started to type the unscrambled word into the text box because autocorrect would suggest the word but also add a whitespace after it. So whenever I clicked the word suggested by autocorrect and submitted it the app would send back feedback saying that the input was incorrect.

## Possible Future Updates
* Get rid of hardcoded strings
* Instead of using the shared view model in order for GameHighScoreFragment to get the high score data, the navigation component should be used to have GameFragment pass the high score as an argument to GameHighScoreFragment. From what I see, GameHighScoreFragment doesn't need a reference to the shared view model.
* Give the player the ability to change the number of scrambled words given in the game
* Give the player the option to have a countdown timer for each displayed scrambled word

## Screenshots
![Alt text](/../screenshots/UnscrambleScreenshots1.png?raw=true)
![Alt text](/../screenshots/UnscrambleScreenshots2.png?raw=true)
![Alt text](/../screenshots/UnscrambleScreenshots3.png?raw=true)
![Alt text](/../screenshots/UnscrambleScreenshots4.png?raw=true)
![Alt text](/../screenshots/UnscrambleScreenshots5.png?raw=true)
