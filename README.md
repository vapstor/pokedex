# Pokédex — Android (Clean Architecture, Coroutines, Flow & Jetpack Compose)

Aplicação Android desenvolvida em **Kotlin** seguindo os princípios de **Clean Architecture** (`Data`, `Domain`, `Presentation` e `DI`), utilizando **Coroutines** e **Flow** para operações assíncronas e gerenciamento de estado reativo, com interface declarativa em **Jetpack Compose**.

---

## Destaques Técnicos

- **Clean Architecture & Separação de Responsabilidades:**
  - `domain`: modelos de domínio (`Pokemon`, `PokemonsPage`), contrato de repositório (`PokemonRepo`) e casos de uso (`GetPokemonsPageUseCase`) isolados de detalhes de framework.
  - `data`: consumo da PokéAPI com **Retrofit** + `kotlinx.serialization`, mappers (`PokemonMapper`), implementação do repositório (`PokemonRepoImpl`) e estrutura preparada para persistência local (`data/local`).
  - `presentation`: padrão MVVM com `HomeViewModel`, estados de tela imutáveis (`UiState`, `PokemonPageUiState`) e UI em **Jetpack Compose** (`HomeRoute`, `PokedexTheme`).
- **Coroutines & Flow:** fluxo reativo de dados da camada de dados até a UI, com controle explícito de estados de carregamento, sucesso e erro.
- **Jetpack Compose:** construção de componentes modulares e tema customizado em Material 3 (`PokedexTheme`), em evolução contínua.
- **Injeção de Dependências & Build:** **Dagger Hilt** (`HiltModules.kt`) e gerenciamento centralizado de dependências via **Gradle Version Catalog** (`gradle/libs.versions.toml`).

---

## Estrutura do Projeto

```text
app/src/main/java/br/com/vapstor/pokedex
├── data
│   ├── local          # Camada de persistência local (preparada para Room)
│   ├── mapper         # Conversão de DTOs para modelos de domínio
│   ├── model          # DTOs serializáveis (@Serializable)
│   ├── remote         # Definição dos endpoints Retrofit (PokemonAPI)
│   └── repository     # Implementação de PokemonRepo (PokemonRepoImpl)
├── di                 # Módulos Hilt (HiltModules)
├── domain
│   ├── model          # Entidades/modelos de domínio
│   ├── repository     # Interfaces de repositório (PokemonRepo)
│   └── usecase        # Casos de uso (GetPokemonsPageUseCase)
├── presentation
│   ├── state          # Estados de tela (UiState, PokemonPageUiState)
│   ├── theme          # Tema e paleta Material 3 (Theme.kt)
│   ├── view.home      # Tela principal, componentes Compose e HomeActivity
│   └── viewmodel      # HomeViewModel
└── PokedexApplication