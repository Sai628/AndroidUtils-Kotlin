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

@file:Suppress("NOTHING_TO_INLINE")

package com.sai628.androidutils.kotlin.view

import android.graphics.Bitmap
import android.support.annotation.Px
import android.support.annotation.RequiresApi
import android.support.annotation.StringRes
import android.support.v4.view.ViewCompat
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver


inline fun View.doOnNextLayout(crossinline action: (view: View) -> Unit) {
    addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
        override fun onLayoutChange(
                v: View,
                left: Int,
                top: Int,
                right: Int,
                bottom: Int,
                oldLeft: Int,
                oldTop: Int,
                oldRight: Int,
                oldBottom: Int
        ) {
            v.removeOnLayoutChangeListener(this)
            action(v)
        }
    })
}


inline fun View.doOnLayout(crossinline action: (view: View) -> Unit) {
    if (ViewCompat.isLaidOut(this) && !isLayoutRequested) {
        action(this)
    } else {
        doOnNextLayout { action(it) }
    }
}


inline fun View.doOnPreDraw(crossinline action: (view: View) -> Unit) {
    val observer = viewTreeObserver
    observer.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
        override fun onPreDraw(): Boolean {
            action(this@doOnPreDraw)
            when {
                observer.isAlive -> observer.removeOnPreDrawListener(this)
                else -> viewTreeObserver.removeOnPreDrawListener(this)
            }
            return true
        }
    })
}


@RequiresApi(16)
inline fun View.announceForAccessibility(@StringRes resId: Int) {
    val announcement = resources.getString(resId)
    announceForAccessibility(announcement)
}


@RequiresApi(17)
inline fun View.updatePaddingRelative(
        @Px start: Int = paddingStart,
        @Px top: Int = paddingTop,
        @Px end: Int = paddingEnd,
        @Px bottom: Int = paddingBottom
) {
    setPaddingRelative(start, top, end, bottom)
}


inline fun View.updatePadding(
        @Px left: Int = paddingLeft,
        @Px top: Int = paddingTop,
        @Px right: Int = paddingRight,
        @Px bottom: Int = paddingBottom
) {
    setPadding(left, top, right, bottom)
}


inline fun View.setPadding(@Px size: Int) {
    setPadding(size, size, size, size)
}


inline fun View.updateLayoutParams(block: ViewGroup.LayoutParams.() -> Unit) {
    updateLayoutParams<ViewGroup.LayoutParams>(block)
}


@JvmName("updateLayoutParamsTyped")
inline fun <reified T : ViewGroup.LayoutParams> View.updateLayoutParams(block: T.() -> Unit) {
    val params = layoutParams as T
    block(params)
    layoutParams = params
}


inline fun View.postDelayed(delayInMillis: Long, crossinline action: () -> Unit): Runnable {
    return Runnable { action() }.also { postDelayed(it, delayInMillis) }
}


@RequiresApi(16)
inline fun View.postOnAnimationDelayed(delayInMillis: Long, crossinline action: () -> Unit): Runnable {
    return Runnable { action() }.also { postOnAnimationDelayed(it, delayInMillis) }
}


fun View.toBitmap(config: Bitmap.Config = Bitmap.Config.ARGB_8888): Bitmap {
    if (!ViewCompat.isLaidOut(this)) {
        throw IllegalStateException("View needs to be laid out before calling toBitmap()")
    }

    TODO("applyCanvas not implement yet")
}


inline var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }


inline var View.isInvisible: Boolean
    get() = visibility == View.INVISIBLE
    set(value) {
        visibility = if (value) View.INVISIBLE else View.VISIBLE
    }


inline var View.isGone: Boolean
    get() = visibility == View.GONE
    set(value) {
        visibility = if (value) View.GONE else View.VISIBLE
    }


