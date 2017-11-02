package com.kkagurazaka.taiyaki.sample

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog

class TextDialogFragment : DialogFragment() {

    private val message
        get() = arguments?.getString(KEY_MESSAGE, "")

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
            AlertDialog.Builder(activity!!)
                    .setMessage(message)
                    .create()

    companion object Factory {

        private const val KEY_MESSAGE = "key.message"

        fun newInstance(message: String): TextDialogFragment =
                TextDialogFragment().apply {
                    arguments = Bundle().apply {
                        putString(KEY_MESSAGE, message)
                    }
                }
    }
}
