package tw.roy.deng.codility.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPreferenceHelper {
    companion object {
        private const val SEARCH_QUERY = "Search Query"
        private var prefs: SharedPreferences? = null

        @Volatile
        private var instance: SharedPreferenceHelper? = null

        fun getInstance(context: Context): SharedPreferenceHelper {
            synchronized(this) {
                val _instance = instance

                if (_instance == null) {
                    prefs = context.getSharedPreferences("GitHub", Context.MODE_PRIVATE)
                    instance = _instance
                }

                return SharedPreferenceHelper()
            }
        }
    }

    fun getSearchQuery() = prefs?.getString(SEARCH_QUERY, "TheresaLin")

    fun saveSearchQuery(q: String) {
        prefs?.edit(commit = true) {
            putString(SEARCH_QUERY, q).apply()
        }
    }
}