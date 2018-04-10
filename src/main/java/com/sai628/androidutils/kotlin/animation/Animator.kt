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

package com.sai628.androidutils.kotlin.animation

import android.animation.Animator
import android.support.annotation.RequiresApi


fun Animator.doOnStart(action: (animator: Animator) -> Unit): Animator.AnimatorListener = addListener(onStart = action)
fun Animator.doOnEnd(action: (animator: Animator) -> Unit): Animator.AnimatorListener = addListener(onEnd = action)
fun Animator.doOnCancel(action: (animator: Animator) -> Unit): Animator.AnimatorListener = addListener(onCancel = action)
fun Animator.doOnRepeat(action: (animator: Animator) -> Unit): Animator.AnimatorListener = addListener(onRepeat = action)

@RequiresApi(19)
fun Animator.doOnPause(action: (animator: Animator) -> Unit): Animator.AnimatorPauseListener = addPauseListener(onPause = action)
@RequiresApi(19)
fun Animator.doOnResume(action: (animator: Animator) -> Unit): Animator.AnimatorPauseListener = addPauseListener(onResume = action)


fun Animator.addListener(
        onStart: ((animator: Animator) -> Unit)? = null,
        onEnd: ((animator: Animator) -> Unit)? = null,
        onCancel: ((animator: Animator) -> Unit)? = null,
        onRepeat: ((animator: Animator) -> Unit)? = null
): Animator.AnimatorListener {

    val listener = object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator) {
            onStart?.invoke(animation)
        }


        override fun onAnimationEnd(animation: Animator) {
            onEnd?.invoke(animation)
        }


        override fun onAnimationCancel(animation: Animator) {
            onCancel?.invoke(animation)
        }


        override fun onAnimationRepeat(animation: Animator) {
            onRepeat?.invoke(animation)
        }
    }

    addListener(listener)
    return listener
}


@RequiresApi(19)
fun Animator.addPauseListener(
        onPause: ((animator: Animator) -> Unit)? = null,
        onResume: ((animator: Animator) -> Unit)? = null
): Animator.AnimatorPauseListener {

    val listener = object : Animator.AnimatorPauseListener {
        override fun onAnimationPause(animation: Animator) {
            onPause?.invoke(animation)
        }


        override fun onAnimationResume(animation: Animator) {
            onResume?.invoke(animation)
        }
    }

    addPauseListener(listener)
    return listener
}
