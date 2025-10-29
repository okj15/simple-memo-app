package com.example.simplememo.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.simplememo.ui.theme.SimpleMemoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoScreen(
    text: String,
    placeholder: String,
    onTextChange: (String) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "クイックメモ") }
            )
        }
    ) { innerPadding ->
        TextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            placeholder = { Text(text = placeholder) },
            singleLine = false,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Default)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun MemoScreenPreview() {
    SimpleMemoTheme {
        MemoScreen(text = "", placeholder = "ここにメモ…（自動保存）", onTextChange = {})
    }
}
