package com.example.utilities

//Sealed classes are often used in applications to model different states in
// a process, such as loading, success, and error states in a network request.

sealed class ResourceState <T>{

  class Loading<T>:ResourceState<T>()
  data class Success<T>(val data:T):ResourceState<T>()
    data class Error<T>(val error:String):ResourceState<T>()

}