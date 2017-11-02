package com.kkagurazaka.taiyaki

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.kkagurazaka.taiyaki.internal.TaiyakiImpl

@Deprecated(message = "", replaceWith = ReplaceWith("CanHandle"))
interface HasTaiyaki<in T : DialogRequest> {

    val taiyaki: Taiyaki<T>

    fun onDialogRequest(request: T)
}

interface CanHandle<in T : DialogRequest> : HasTaiyaki<T> {

    override val taiyaki: Taiyaki<T>

    fun request(request: T) {
        taiyaki.request(request)
    }

    override fun onDialogRequest(request: T)
}

interface Taiyaki<in T : DialogRequest> {

    fun request(request: T)

    fun onActivityPaused()

    fun onActivityResumed()

    companion object {

        val LifeCycleCallbacks: Application.ActivityLifecycleCallbacks =
                object : Application.ActivityLifecycleCallbacks {

                    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                    }

                    override fun onActivityDestroyed(activity: Activity?) {
                    }

                    override fun onActivityPaused(activity: Activity?) {
                        if (activity is CanHandle<*>) {
                            activity.taiyaki.onActivityPaused()
                        }
                    }

                    override fun onActivityResumed(activity: Activity?) {
                        if (activity is CanHandle<*>) {
                            activity.taiyaki.onActivityResumed()
                        }
                    }

                    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
                    }

                    override fun onActivityStarted(activity: Activity?) {
                    }

                    override fun onActivityStopped(activity: Activity?) {
                    }
                }
    }
}

fun <T : DialogRequest> Taiyaki(holder: CanHandle<T>, showLatestOnly: Boolean = true): Taiyaki<T> =
        TaiyakiImpl(holder, showLatestOnly)
