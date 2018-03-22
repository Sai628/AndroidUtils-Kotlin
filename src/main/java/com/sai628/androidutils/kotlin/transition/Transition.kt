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

package com.sai628.androidutils.kotlin.transition

import android.support.annotation.RequiresApi
import android.transition.Transition


@RequiresApi(19)
fun Transition.doOnStart(action: (transition: Transition) -> Unit) {
    addListener(onStart = action)
}


@RequiresApi(19)
fun Transition.doOnEnd(action: (transition: Transition) -> Unit) {
    addListener(onEnd = action)
}


@RequiresApi(19)
fun Transition.doOnCancel(action: (transition: Transition) -> Unit) {
    addListener(onCancel = action)
}


@RequiresApi(19)
fun Transition.doOnPause(action: (transition: Transition) -> Unit) {
    addListener(onPause = action)
}


@RequiresApi(19)
fun Transition.doOnResume(action: (transition: Transition) -> Unit) {
    addListener(onResume = action)
}


@RequiresApi(19)
fun Transition.addListener(
        onStart: ((transition: Transition) -> Unit)? = null,
        onEnd: ((transition: Transition) -> Unit)? = null,
        onCancel: ((transition: Transition) -> Unit)? = null,
        onPause: ((transition: Transition) -> Unit)? = null,
        onResume: ((transition: Transition) -> Unit)? = null
) {
    addListener(object : Transition.TransitionListener {
        override fun onTransitionStart(transition: Transition) {
            onStart?.invoke(transition)
        }


        override fun onTransitionEnd(transition: Transition) {
            onEnd?.invoke(transition)
        }


        override fun onTransitionCancel(transition: Transition) {
            onCancel?.invoke(transition)
        }


        override fun onTransitionPause(transition: Transition) {
            onPause?.invoke(transition)
        }


        override fun onTransitionResume(transition: Transition) {
            onResume?.invoke(transition)
        }
    })
}
