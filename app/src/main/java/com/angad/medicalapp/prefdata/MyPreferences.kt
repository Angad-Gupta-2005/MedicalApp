package com.angad.medicalapp.prefdata

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//    Creating an instance of preferences
private val Context.dataStore by preferencesDataStore("user_prefs")

class MyPreferences(private val context: Context) {

    companion object{
        private val USER_ID_KEY = stringPreferencesKey("user_id")
        private val IS_LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")
    }

//    Function that save the userId in the preference datastore
    suspend fun saveUserID(userId: String){
        context.dataStore.edit { pref->
            pref[USER_ID_KEY] = userId
        }
    }

//    Function that save the login status of the user into the preference datastore
    suspend fun saveLoginStatus(isLoggedIn: Boolean){
        context.dataStore.edit { pref->
            pref[IS_LOGGED_IN_KEY] = isLoggedIn
        }
    }

//    Function that fetch the login status of the user from the preference datastore
    val isLoggedIn: Flow<Boolean> = context.dataStore.data.map {
        it[IS_LOGGED_IN_KEY] ?: false
    }

//     Function that use to fetch userId from preferences
    val getUserId: Flow<String?> = context.dataStore.data.map {
        it[USER_ID_KEY]
    }

//    Function that delete the data from preferences if the user is logout
    suspend fun clearUser(){
        context.dataStore.edit {
            it.clear()
        }
    }

}