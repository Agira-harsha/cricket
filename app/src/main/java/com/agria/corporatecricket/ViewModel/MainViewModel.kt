package com.agria.corporatecricket.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agria.corporatecricket.Dtos.TournamentResponse
import com.agria.corporatecricket.Handlers.recipeService
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel()
{

    private val _categorieState = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categorieState

    init {
        fetchCategories()
    }


    private fun fetchCategories()
    {
        viewModelScope.launch {
            try {
                val response = recipeService.getCategories()
                _categorieState.value = _categorieState.value.copy(
                    list = response,
                    loading = false,
                    error = null
                )

            }catch (e: Exception){
                _categorieState.value = _categorieState.value.copy(
                    loading = false,
                    error = "Error fetching Categories ${e.message}"
                )
            }
        }
    }

    data class RecipeState(
        val loading: Boolean = true,
        val list: List<TournamentResponse> = emptyList(),
        val error: String? = null
    )

}