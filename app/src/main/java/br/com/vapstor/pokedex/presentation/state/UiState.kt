package br.com.vapstor.pokedex.presentation.state

sealed interface UiState {
    data object Idle : UiState
    data object Loading : UiState
    data class Success<T>(val data: T) : UiState
    data class Error(val message: String) : UiState
}