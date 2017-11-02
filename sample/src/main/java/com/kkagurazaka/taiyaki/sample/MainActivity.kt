package com.kkagurazaka.taiyaki.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.kkagurazaka.taiyaki.CanHandle
import com.kkagurazaka.taiyaki.Taiyaki
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), CanHandle<MainDialogRequest> {

    override val taiyaki = Taiyaki(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            Toast.makeText(this, "After 3 secs, dialog will show.", Toast.LENGTH_SHORT).show()

            thread {
                Thread.sleep(3000)

                // showDialog(DialogWithParamRequest("FooBar")) // <- IllegalStateException!
                request(DialogWithParamRequest("FooBar"))
            }
        }
    }

    override fun onDialogRequest(request: MainDialogRequest) {
        showDialog(request)
    }

    private fun showDialog(request: MainDialogRequest) {
        when (request) {
            is DialogWithParamRequest -> {
                TextDialogFragment.newInstance("Param: ${request.param}")
            }
            is DialogWithoutParamRequest -> {
                TextDialogFragment.newInstance("No Param")
            }
        }.show(supportFragmentManager, null)
    }
}
