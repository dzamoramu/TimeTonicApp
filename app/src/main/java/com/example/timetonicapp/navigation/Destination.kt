package com.example.timetonicapp.navigation

sealed class Destination(
    val route: String,
) {

    object Login : Destination("screenLogin")
   // object Landing : Destination("screenLanding/{oU}/{sessionKey}")
}

//{
//        fun createRoute(oU: String, sessionKey: String) = "screenLanding/$oU/$sessionKey"
//    }