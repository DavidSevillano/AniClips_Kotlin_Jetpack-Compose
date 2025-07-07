package com.burixer85.aniclips.domain.model

sealed class OperationResult<out T> {
    data class Success<out T>(val data: T) : OperationResult<T>()
    object EmptyFields : OperationResult<Nothing>()
    object InvalidCredentials : OperationResult<Nothing>()
    object NetworkError : OperationResult<Nothing>()
    object InvalidEmailFormat : OperationResult<Nothing>()
    object RepeatPasswordError : OperationResult<Nothing>()
}