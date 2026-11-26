package br.com.vapstor.pokedex.presentation.view.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.vapstor.pokedex.presentation.theme.PokedexTheme
import br.com.vapstor.pokedex.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_home)

        enableEdgeToEdge()
        setContent {
            PokedexTheme {
                HomeRoute(
                    viewModel = homeViewModel,
                    onPokemonClick = { pokemon ->
                        // Futura navegação para detalhes do Pokémon
                    },
                )
            }
        }
    }
}
