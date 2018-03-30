/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sai628.androidutils.kotlin.os

import android.os.Handler
import android.os.Message


@PublishedApi
internal fun Handler.postDelayedWithToken(runnable: Runnable, token: Any?, delayInMillis: Long) {
    val message = Message.obtain(this, runnable)
    message.obj = token
    sendMessageDelayed(message, delayInMillis)
}


inline fun Handler.postDelayed(
        delayInMillis: Long,
        token: Any? = null,
        crossinline action: () -> Unit
): Runnable {
    val runnable = Runnable { action() }
    if (token == null) {
        postDelayed(runnable, delayInMillis)
    } else {
        postDelayedWithToken(runnable, token, delayInMillis)
    }

    return runnable
}


inline fun Handler.postAtTime(
        uptimeMillis: Long,
        token: Any? = null,
        crossinline action: () -> Unit
): Runnable {
    val runnable = Runnable { action() }
    postAtTime(runnable, token, uptimeMillis)
    return runnable
}
