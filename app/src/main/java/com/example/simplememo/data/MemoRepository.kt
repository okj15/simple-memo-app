package com.example.simplememo.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DATASTORE_NAME = "memo_preferences"
private val MEMO_KEY = stringPreferencesKey("memo")

private val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME)

class MemoRepository(private val context: Context) {
    val memoFlow: Flow<String> = context.dataStore.data.map { preferences: Preferences ->
        preferences[MEMO_KEY] ?: ""
    }

    suspend fun saveMemo(text: String) {
        context.dataStore.edit { preferences ->
            preferences[MEMO_KEY] = text
        }
    }
}
