package com.team2.domain.common


import java.lang.Exception

sealed class Resource<out T> {
    class Success<T>(val data: T? = null): Resource<T>()
    class Error(val exception: Exception): Resource<Nothing>()
}