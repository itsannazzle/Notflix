package com.nextint.core.utils

import android.os.Handler
import android.os.Looper
import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutor @VisibleForTesting constructor(
    private val diskIO: Executor
) {
    companion object;

    constructor() : this(
        Executors.newSingleThreadExecutor()
    )

    fun diskIO(): Executor = diskIO

    private class MainThreadExecutor : Executor {
        val mainThreadHandlerThread = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandlerThread.post(command)
        }

    }
}