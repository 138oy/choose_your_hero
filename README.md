# Choose Your Hero Android App

## Why this app exists

The app is an Effective internship project.
It's also been a great and easy project to try some of the essential Android development tools on.

## What it does

The app shows a list of Marvel heroes. The data is received from [Marvel API](https://developer.marvel.com/docs#!/public/getCreatorCollection_get_0).

The main screen has a Marvel logo, a slogan and a carousel of the heroes. You can tap on the card to open the screen containing the information card (so that you can also read some tea about the hero you tapped on yay).

The app supports caching, light/dark theme, device orientation change, edge-to-edge screen, right-to-left writing and english and russian locales.

## UI layout

The layout of the screens in both portrait and landscape orientations:

|             | Portrait                                                              | Landscape                                                                 |
|-------------|-----------------------------------------------------------------------|---------------------------------------------------------------------------|
| main screen | <img src="assets/layout/PORTRAIT/choosing_screen.png" height="400">   | <img src="assets/layout/LANDSCAPE/choosing_screen.png" height="400">      |
| info screen | <img src="assets/layout/PORTRAIT/hero_ info_screen.png" height="400"> | <img src="assets/layout/LANDSCAPE/hero_info_screen.png.png" height="400"> |                                                                       |

## Screenshots

The dark theme and portrait orientation:

The light theme and landscape orientation:

## Topics of labs

1. Jetpack Compose
2. Navigation
3. API Calls using Retrofit
4. Caching using Room
5. App Architecture
6. Notification using Firebase
7. Overall project additions: localization, rtl and edge-to-edge, adaptive ui support.

## How to run

## How to send notifications

https://fcm.googleapis.com/fcm/send

## Known issues

Snapper: Well, it's kinda off :( It doesn't let the last item in the list be the "current" one
