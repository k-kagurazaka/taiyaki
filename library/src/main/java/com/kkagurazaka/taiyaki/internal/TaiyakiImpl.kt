package com.kkagurazaka.taiyaki.internal

import com.kkagurazaka.taiyaki.DialogRequest
import com.kkagurazaka.taiyaki.CanHandle
import com.kkagurazaka.taiyaki.Taiyaki
import java.util.*

internal class TaiyakiImpl<in T : DialogRequest>(
        private val holder: CanHandle<T>,
        private val showLatestOnly: Boolean
) : Taiyaki<T> {

    private var isPaused = true

    private val requestQueue = ArrayDeque<T>()

    @Synchronized
    override fun request(request: T) {
        if (isPaused) {
            requestQueue.add(request)
        } else {
            holder.onDialogRequest(request)
        }
    }

    @Synchronized
    override fun onActivityPaused() {
        isPaused = true
    }

    @Synchronized
    override fun onActivityResumed() {
        if (showLatestOnly) {
            requestQueue.pollLast()?.let { request ->
                requestQueue.clear()
                holder.onDialogRequest(request)
            }
        } else {
            while (true) {
                val request = requestQueue.pollFirst() ?: break
                holder.onDialogRequest(request)
            }
        }

        isPaused = false
    }
}
