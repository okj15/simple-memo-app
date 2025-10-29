package com.example.simplememo

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.simplememo.data.MemoRepository
import com.example.simplememo.ui.MemoUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val SAVE_DEBOUNCE_MS = 300L

class MemoViewModel(private val repository: MemoRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(MemoUiState())
    val uiState: StateFlow<MemoUiState> = _uiState.asStateFlow()

    private var saveJob: Job? = null

    init {
        viewModelScope.launch {
            repository.memoFlow.collect { storedText ->
                _uiState.value = _uiState.value.copy(memoText = storedText)
            }
        }
    }

    fun onTextChanged(newText: String) {
        _uiState.value = _uiState.value.copy(memoText = newText)
        saveJob?.cancel()
        saveJob = viewModelScope.launch {
            delay(SAVE_DEBOUNCE_MS)
            repository.saveMemo(newText)
        }
    }

    companion object {
        fun Factory(context: Context): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val repository = MemoRepository(context)
                return MemoViewModel(repository) as T
            }
        }
    }
}
