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

package com.sai628.androidutils.kotlin.util

import android.support.annotation.RequiresApi
import android.util.Range


@RequiresApi(21)
inline infix fun <T: Comparable<T>> T.rangeTo(that: T): Range<T> = Range(this, that)


// 范围的加运算, 如: [0, 3] + 5 -> [0, 5]
@RequiresApi(21)
inline operator fun <T: Comparable<T>> Range<T>.plus(value: T): Range<T> = extend(value)


// 范围的加运算, 如: [0, 3] + [2, 5] -> [0, 5]
@RequiresApi(21)
inline operator fun <T: Comparable<T>> Range<T>.plus(other: Range<T>): Range<T> = extend(other)


// 范围的and运算, 如: [0, 3] and [2, 5] -> [2, 3]
// 若两范围不相交时, 将会抛出异常 IllegalArgumentException
@RequiresApi(21)
inline infix fun <T: Comparable<T>> Range<T>.and(other: Range<T>): Range<T> = intersect(other)


@RequiresApi(21)
fun <T: Comparable<T>> Range<T>.toClosedRange(): ClosedRange<T> = object: ClosedRange<T> {
    override val start: T get() = lower
    override val endInclusive: T get() = upper
}


@RequiresApi(21)
fun <T: Comparable<T>> ClosedRange<T>.toRange(): Range<T> = Range(start, endInclusive)
