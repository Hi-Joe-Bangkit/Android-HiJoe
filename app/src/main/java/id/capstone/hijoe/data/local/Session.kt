package id.capstone.hijoe.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import javax.inject.Inject

class Session
@Inject constructor(
        context: Context,
        private val gson: Gson
) {
    private val sp: SharedPreferences by lazy {
        context.getSharedPreferences(SESSION_NAME, Context.MODE_PRIVATE)
    }

    private val spe: SharedPreferences.Editor by lazy {
        sp.edit()
    }

    fun reset() {
        spe.clear().apply()
    }

    var isFirstTime: Boolean
        get() = sp.getBoolean(PREF_FIRST_TIME, true)
        set(value) {
            spe.putBoolean(PREF_FIRST_TIME, value).apply()
        }

    companion object {
        private const val SESSION_NAME = "hijoe session"
        private const val PREF_FIRST_TIME = "is first time"
    }
}