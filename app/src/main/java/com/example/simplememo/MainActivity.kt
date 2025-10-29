package com.example.simplememo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.simplememo.ui.MemoScreen
import com.example.simplememo.ui.theme.SimpleMemoTheme

class MainActivity : ComponentActivity() {
    private val memoViewModel: MemoViewModel by viewModels {
        MemoViewModel.Factory(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleMemoTheme {
                val uiState by memoViewModel.uiState.collectAsState()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MemoScreen(
                        text = uiState.memoText,
                        placeholder = getString(R.string.memo_placeholder),
                        onTextChange = memoViewModel::onTextChanged
                    )
                }
            }
        }
    }
}
