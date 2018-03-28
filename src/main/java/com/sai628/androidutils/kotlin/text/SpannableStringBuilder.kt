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

package com.sai628.androidutils.kotlin.text

import android.graphics.Typeface
import android.support.annotation.ColorInt
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.SpannedString
import android.text.style.*


inline fun buildSpannedString(builderAction: SpannableStringBuilder.() -> Unit): SpannedString {
    val builder = SpannableStringBuilder()
    builder.builderAction()
    return SpannedString(builder)
}


inline fun SpannableStringBuilder.inSpans(
        vararg spans: Any,
        builderAction: SpannableStringBuilder.() -> Unit
): SpannableStringBuilder {
    val start = length
    builderAction()
    for (span in spans) {
        setSpan(span, start, length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
    }

    return this
}


inline fun SpannableStringBuilder.inSpans(
        span: Any,
        builderAction: SpannableStringBuilder.() -> Unit
): SpannableStringBuilder {
    val start = length
    builderAction()
    setSpan(span, start, length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

    return this
}


inline fun SpannableStringBuilder.bold(buildAction: SpannableStringBuilder.() -> Unit) =
        inSpans(StyleSpan(Typeface.BOLD), buildAction)

inline fun SpannableStringBuilder.italic(builderAction: SpannableStringBuilder.() -> Unit) =
        inSpans(StyleSpan(Typeface.ITALIC), builderAction)

inline fun SpannableStringBuilder.underline(builderAction: SpannableStringBuilder.() -> Unit) =
        inSpans(UnderlineSpan(), builderAction)

inline fun SpannableStringBuilder.color(@ColorInt color: Int, builderAction: SpannableStringBuilder.() -> Unit) =
        inSpans(ForegroundColorSpan(color), builderAction)

inline fun SpannableStringBuilder.backgroundColor(@ColorInt color: Int, builderAction: SpannableStringBuilder.() -> Unit) =
        inSpans(BackgroundColorSpan(color), builderAction)

inline fun SpannableStringBuilder.strikeThrough(builderAction: SpannableStringBuilder.() -> Unit) =
        inSpans(StrikethroughSpan(), builderAction)

inline fun SpannableStringBuilder.scale(proportion: Float, builderAction: SpannableStringBuilder.() -> Unit) =
        inSpans(RelativeSizeSpan(proportion), builderAction)
