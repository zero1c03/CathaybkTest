package com.weber.cathaybktest.fragment

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

open class BaseFragment : Fragment() {

    fun showSnackBar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }

    fun showLoading(flLoading: View, show: Boolean) {
        flLoading.visibility = if (show) View.VISIBLE else View.GONE
    }
}