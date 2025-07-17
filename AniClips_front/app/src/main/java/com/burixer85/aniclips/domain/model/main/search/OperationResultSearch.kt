package com.burixer85.aniclips.domain.model.main.search

sealed class OperationResultSearch<out T> {
    data class Success<out T>(val data: T) : OperationResultSearch<T>()
    object NetworkError : OperationResultSearch<Nothing>()
}