package com.example.assignment.coreBase

sealed class APIState<out T> {
    class ShowHideDialog(val showHide: Boolean) : APIState<Any>()

    class Error(val errorMessage: String) : APIState<Any>()

    class NetworkResponseSuccess(val response: Any) : APIState<Any>()
}
