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

package com.sai628.androidutils.kotlin.text

import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned


inline fun CharSequence.toSpannable(): Spannable = SpannableString.valueOf(this)

inline fun Spannable.clearSpans() = getSpans<Any>().forEach { removeSpan(it) }
inline operator fun Spannable.plusAssign(span: Any) = setSpan(span, 0, length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
inline operator fun Spannable.minusAssign(span: Any) = removeSpan(span)


inline operator fun Spannable.set(start: Int, end: Int, span: Any) {
    setSpan(span, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
}


inline operator fun Spannable.set(range: IntRange, span: Any) {
    setSpan(span, range.start, range.endInclusive, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
}
