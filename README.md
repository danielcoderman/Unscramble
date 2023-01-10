Unscramble App
===================================
This is an app I first built by following the google codelabs (https://developer.android.com/courses/pathways/android-basics-kotlin-unit-3-pathway-3). I then extended its functionality by adding a highscore feature, and I also fixed bugs that still existed after I completed the codelabs.

Unscramble is a single player game app that displays scrambled words. To play the game, the player has to make a word using all the letters from the displayed scrambled word. The player gains 20 points for each word unscrambled correctly. If the player can't unscramble the word correctly they have the option to skip the word and receive a different scrambled word. The player is also able to view their highscore in the game.

Some background to why I added this feature is....

Android Components/Features Used
===================================
* AndroidViewModel
* Navigation Component
* SafeArgs
* Fragment
* Activity
* LiveData
* Data Binding
* DialogFragment
* MaterialAlertDialogBuilder
* MenuProvider
* SharedPreferences

Basic Structure
==================================
The android architectural principle I'm using is driving the UI from a model.
FinalScoreDialogFragment

Features Added
==================================
Player can view their highscore
-------------------------------
To implement this feature I added another fragment to the app, called GameHighScoreFragment. The sole purpose of this fragment is to show the highscore of the player. The GameHighScoreFragment gets access to the shared view model managed delegate class activityViewModels. Using activityViewModels allows the ViewModel used in the app to be the same for all fragments in the app because it's scoped to the activity. Since access to the ViewModel is needed in GameHighScoreFragment, the data binding is setup here so that the observers in the corresponding layout of the fragment are notified whenever relevant data from the ViewModel changes.

Adding the GameHighScoreFragment also required the need to navigate between fragments so I used the Navigation Component. This allows for navigation from the GameFragment to the GameHighScoreFragment

This feature also caused me to make some changes in the GameViewModel because I needed a way to save the highscore data locally and I thought it would be fitting for the ViewModel to be aware of the highscore data. Before it was a ViewModel but I switched it to an AndroidViewModel because I needed to reference the Application context within the ViewModel. An AndroidViewModel is Application context aware according to the documentation on the android developers website. When the AndroidViewModel is first created the highscore property is initialized by fetching the data saved using SharedPreferences, and whenever the score is greater than the highscore, the highscore gets updated and saved locally. This way if the player closes the app the highscore is saved.

Once again, adding a highscore functionalty lead me create a menu. Since OnCreateMenuOptions was recently deprecated I made a menu using MenuProvider. Using the MenuProvider I was able to add an option in the appbar that shows the highscore when clicked.

Bugs Fixed
==================================
Dialog Disappearing on device rotation

Possible Future Updates
==================================
Storing words in Room instead
