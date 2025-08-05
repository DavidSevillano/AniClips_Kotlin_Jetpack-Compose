package com.burixer85.aniclips.domain.model.main.profile

sealed class OperationResultProfile<out T> {
    data class Success<out T>(val data: T) : OperationResultProfile<T>()
    object NetworkError : OperationResultProfile<Nothing>()
}