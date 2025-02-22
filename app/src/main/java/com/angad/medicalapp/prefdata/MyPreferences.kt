package com.angad.medicalapp.prefdata

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

//    Creating an instance of preferences
private val Context.dataStore by preferencesDataStore("user_prefs")

class MyPreferences(private val context: Context) {

    companion object{
        private val USER_ID_KEY = stringPreferencesKey("user_id")
    }

//    Function that save the userId in the preference datastore
    suspend fun saveUserID(userId: String){
        context.dataStore.edit { pref->
            pref[USER_ID_KEY] = userId
        }
    }

//    Function that get the userId from the preference
//    suspend fun getUser(): String?{
//        val preferences = context.dataStore.data.first()
//        return preferences[USER_ID_KEY]
//    }

//    OR we can also use this to fetch userId from preferences
    val getUserId: Flow<String?> = context.dataStore.data.map {
        it[USER_ID_KEY]
    }

//    Function that delete the data from preferences if the user is logout
    suspend fun clearUserId(){
        context.dataStore.edit {
            it.clear()
        }
    }

}