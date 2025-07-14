package com.burixer85.aniclips.domain.model.auth

sealed class OperationResultAuth<out T> {
    data class Success<out T>(val data: T) : OperationResultAuth<T>()
    object EmptyFields : OperationResultAuth<Nothing>()
    object InvalidCredentials : OperationResultAuth<Nothing>()
    object NetworkError : OperationResultAuth<Nothing>()
    object InvalidEmailFormat : OperationResultAuth<Nothing>()
    object RepeatPasswordError : OperationResultAuth<Nothing>()
}