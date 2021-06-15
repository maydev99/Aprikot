# Aprikot
Aprikot
Recipe Application
Udacity Kotlin Android Developer NanoDegree

by Michael May

![Image](https://github.com/maydev99/Aprikot/blob/master/aprikot_tripple_shot.png)

## Description:
Aprikot is a food recipe app which uses data from TheMealDB (https://www.themealdb.com)
This is a free to use API that does not require an api key. 

When starting the app you will see a categories screen that represents various meal categories. There are three fragments attached to the MainActivity all connected through Navigation Manager and Bottom Navigation bar. Along with the categories screen you can navigate to either the Favorites or Settings Screen.


## Categories Screen
Select a category and you are taken to a new activity where a list of recipes is displayed.
Scroll through the list, find  and tap on your desired recipe. You are then taken to the Preparation Screen.


## Preparation Screen
The Preparation Screen has the ingredients, instructions and image of the meal to help you prepare this meal for yourself.
The ingredients and instructions are scrollable.
There is a Heart Icon in the top right section of the screen. Tap the heart icon and the recipe is added to your Favorites Screen. Tap is again and the recipe is removed from the Favorites.
Pressing the Back Arrow on the upper left take you back to the Categories Screen
The Back Button will take you back to the Recipe List Screen.


## Favorites Screen
The Favorites Screen shows any recipe that you have marked as favorites in the Preparation Screen. If there are no favorites a dialog will show indicating that.
Tap on a recipe and you will be taken to the Preparations Screen to view the details of the recipe.


## Settings Screen
At this time the settings screen only has one setting available.
In the Display Section there is a toggle switch to activate Wake Lock which will keep the Preparations screen awake so the user can keep the recipe details visible without the device sleeping. This is optional and is off by default. If left on, a notification will inform you that this setting is on.
