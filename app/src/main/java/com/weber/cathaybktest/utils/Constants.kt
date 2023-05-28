package com.weber.cathaybktest.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.text.SpannableString
import android.text.Spanned
import android.text.style.URLSpan
import android.widget.TextView

object Constants {

    fun getSharedPreference(application: Application): SharedPreferences {
        return application.getSharedPreferences("pref", Context.MODE_PRIVATE)
    }

    fun getLangSharedPreferences(application: Application): String {
        val pref = application.getSharedPreferences("pref", Context.MODE_PRIVATE)
        return pref.getString("lang", "en").toString()
    }

    fun setLangSharedPreferences(application: Application, lang: String) {
        val pref = application.getSharedPreferences("pref", Context.MODE_PRIVATE)
        pref.edit()
            .putString("lang", lang)
            .apply()
    }

    fun TextView.hyperlinkStyle() {
        setText(
            SpannableString(text).apply {
                setSpan(
                    URLSpan(""),
                    0,
                    length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            },
            TextView.BufferType.SPANNABLE
        )
    }
}