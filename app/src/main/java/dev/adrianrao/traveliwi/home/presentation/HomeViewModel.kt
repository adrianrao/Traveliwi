package dev.adrianrao.traveliwi.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.adrianrao.traveliwi.home.domain.repository.HomeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {
    init {
        viewModelScope.launch {
            repository.getTravelInformation().onSuccess {
                Log.d("HomeViewModel", it)
            }.onFailure {
                Log.e("HomeViewModel", it.message.toString())
            }
        }
    }
}