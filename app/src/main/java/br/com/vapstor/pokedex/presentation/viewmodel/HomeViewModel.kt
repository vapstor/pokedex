package br.com.vapstor.pokedex.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.vapstor.pokedex.domain.usecase.GetPokemonsPageUseCase
import br.com.vapstor.pokedex.presentation.state.PokemonPageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonsPageUseCase: GetPokemonsPageUseCase
) : ViewModel() {

    private val pageSize = 20
    private var currentOffset = 0
    private var isRequestInFlight = false

    private val _uiState = MutableStateFlow(PokemonPageUiState(isLoading = true))
    val uiState: StateFlow<PokemonPageUiState> = _uiState.asStateFlow()

    init {
        getPokemonsPage(isInitialLoad = true)
    }

    fun getPokemonsPage(isInitialLoad: Boolean = false) {
        val currentState = _uiState.value
        if (isRequestInFlight || (!isInitialLoad && !currentState.hasNextPage)) return

        if (isInitialLoad) {
            currentOffset = 0
        }

        viewModelScope.launch {
            //  Atualiza o estado para refletir que uma solicitação está em andamento
            _uiState.update { state ->
                state.copy(
                    isLoading = isInitialLoad && state.pokemons.isEmpty(),
                    isLoadingMore = !isInitialLoad && state.pokemons.isNotEmpty(),
                    errorMessage = null
                )
            }

            getPokemonsPageUseCase(pageSize, currentOffset)
                .onStart { isRequestInFlight = true }
                .onCompletion { isRequestInFlight = false }
                .catch { exception ->
                    _uiState.update { state ->
                        state.copy(
                            errorMessage = exception.message ?: "Erro desconhecido",
                            isLoading = false,
                            isLoadingMore = false
                        )
                    }
                }
                .collect { page ->
                    val updatedList = if (isInitialLoad) {
                        page.items
                    } else {
                        _uiState.value.pokemons + page.items
                    }

                    currentOffset += page.items.size

                    _uiState.update { state ->
                        state.copy(
                            pokemons = updatedList,
                            isLoading = false,
                            isLoadingMore = false,
                            hasNextPage = page.hasNextPage,
                            errorMessage = null
                        )
                    }
                }
        }
    }
}