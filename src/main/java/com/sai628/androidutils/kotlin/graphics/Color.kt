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

@file:Suppress("NOTHING_TO_INLINE", "WRONG_ANNOTATION_TARGET_WITH_USE_SITE_TARGET_ON_TYPE")

package com.sai628.androidutils.kotlin.graphics

import android.graphics.Color
import android.support.annotation.ColorInt
import android.support.annotation.ColorLong
import android.support.annotation.RequiresApi


@RequiresApi(26)
inline operator fun Color.component1() = getComponent(0)
@RequiresApi(26)
inline operator fun Color.component2() = getComponent(1)
@RequiresApi(26)
inline operator fun Color.component3() = getComponent(2)
@RequiresApi(26)
inline operator fun Color.component4() = getComponent(3)


@RequiresApi(26)
operator fun Color.plus(c: Color): Color {
    if (model != c.model) {
        throw IllegalArgumentException("Color models must match ($model vs ${c.model})")
    }

    val s: Color = if (colorSpace != c.colorSpace) c.convert(colorSpace) else c
    val src = s.components
    val dst = components
    var sa = s.alpha()
    var da = alpha() * (1.0f - sa)

    // Index of the alpha component
    val ai = componentCount - 1

    // Final alpha
    dst[ai] = sa + da

    if (dst[ai] > 0) {
        sa /= dst[ai]
        da /= dst[ai]
    }

    for (i in 0 until ai) {
        dst[i] = src[i] * sa + dst[i] * da
    }

    return Color.valueOf(dst, colorSpace)
}


inline operator fun @receiver:ColorInt Int.component1() = (this shr 24) and 0xff
inline operator fun @receiver:ColorInt Int.component2() = (this shr 16) and 0xff
inline operator fun @receiver:ColorInt Int.component3() = (this shr 8) and 0xff
inline operator fun @receiver:ColorInt Int.component4() = this and 0xff


inline val @receiver:ColorInt Int.alpha get() = (this shr 24) and 0xff
inline val @receiver:ColorInt Int.red get() = (this shr 16) and 0xff
inline val @receiver:ColorInt Int.green get() = (this shr 8) and 0xff
inline val @receiver:ColorInt Int.blue get() = this and 0xff


@get:RequiresApi(26)
inline val @receiver:ColorInt Int.luminance get() = Color.luminance(this)
@RequiresApi(26)
inline fun @receiver:ColorInt Int.toColor(): Color = Color.valueOf(this)
@RequiresApi(26)
@ColorLong
inline fun @receiver:ColorInt Int.toColorLong() = Color.pack(this)


@RequiresApi(26)
inline operator fun @receiver:ColorLong Long.component1() = Color.red(this)
@RequiresApi(26)
inline operator fun @receiver:ColorLong Long.component2() = Color.green(this)
@RequiresApi(26)
inline operator fun @receiver:ColorLong Long.component3() = Color.blue(this)
@RequiresApi(26)
inline operator fun @receiver:ColorLong Long.component4() = Color.alpha(this)


@get:RequiresApi(26)
inline val @receiver:ColorLong Long.alpha get() = Color.alpha(this)
@get:RequiresApi(26)
inline val @receiver:ColorLong Long.red get() = Color.red(this)
@get:RequiresApi(26)
inline val @receiver:ColorLong Long.green get() = Color.green(this)
@get:RequiresApi(26)
inline val @receiver:ColorLong Long.blue get() = Color.blue(this)


@get:RequiresApi(26)
inline val @receiver:ColorLong Long.luminance get() = Color.luminance(this)
@RequiresApi(26)
inline fun @receiver:ColorLong Long.toColor(): Color = Color.valueOf(this)
@RequiresApi(26)
@ColorInt
inline fun @receiver:ColorLong Long.toColorInt() = Color.toArgb(this)


@get:RequiresApi(26)
inline val @receiver:ColorLong Long.isSrgb get() = Color.isSrgb(this)
@get:RequiresApi(26)
inline val @receiver:ColorLong Long.isWideGamut get() = Color.isWideGamut(this)
@get:RequiresApi(26)
inline val @receiver:ColorLong Long.colorSpace get() = Color.colorSpace(this)


@ColorInt
inline fun String.toColorInt(): Int = Color.parseColor(this)
