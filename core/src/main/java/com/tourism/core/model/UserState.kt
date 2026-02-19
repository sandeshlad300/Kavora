package com.tourism.core.model


sealed class UserState {
    object Guest : UserState()
    object LoggedIn : UserState()
}