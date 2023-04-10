package com.dmatesanz.leto.screens.menu.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {

    private val gamesRepository = GameListRepository()

    fun getGames() {
        viewModelScope.launch {
            try {
                val response = gamesRepository.getGames()
                Log.e("XXX", "response is = $response")
                Log.e("XXX", "response body is = ${response.body()}")
                Log.e("XXX", "response isSuccessful = ${response.isSuccessful}")
            } catch (e: Exception) {
                Log.e("XXX", "Exception error = $e")
            }
        }
    }
}
