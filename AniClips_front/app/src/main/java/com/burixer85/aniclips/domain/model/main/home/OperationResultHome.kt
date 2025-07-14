package com.burixer85.aniclips.domain.model.main.home

sealed class OperationResultHome<out T> {
    data class Success<out T>(val data: T) : OperationResultHome<T>()
    object NetworkError : OperationResultHome<Nothing>()
}