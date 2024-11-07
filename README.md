# Fetch-Project

This is a project built for Fetch Hiring. It pulls data from https://fetch-hiring.s3.amazonaws.com/hiring.json and:

- Groups all items by `listId`, showing them as a row of cards or as a grid (toggleable using the list icon in the top right)
- Shows each item in a card including the `name` and `id`
- Removes any items without a valid name.

The app was built using `Android Studio Ladybug | 2024.2.1 Patch 2` and is set to compile and target API 35.

The UI is built using Jetpack Compose and Kotlin Coroutines.
