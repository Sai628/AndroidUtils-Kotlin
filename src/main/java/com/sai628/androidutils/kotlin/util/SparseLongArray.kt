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
import android.util.SparseLongArray


@get:RequiresApi(18)
inline val SparseLongArray.size: Int get() = size()
@RequiresApi(18)
inline fun SparseLongArray.isEmpty(): Boolean = size() == 0
@RequiresApi(18)
inline fun SparseLongArray.isNotEmpty(): Boolean = size() != 0
@RequiresApi(18)
inline fun SparseLongArray.containsKey(key: Int): Boolean = indexOfKey(key) >= 0
@RequiresApi(18)
inline fun SparseLongArray.containsValue(value: Long): Boolean = indexOfValue(value) != -1
@RequiresApi(18)
inline fun SparseLongArray.getOrDefault(key: Int, defaultValue: Long): Long = get(key, defaultValue)
@RequiresApi(18)
inline fun SparseLongArray.getOrElse(key: Int, defaultValue: () -> Long): Long =
        indexOfKey(key).let { if (it != -1) valueAt(it) else defaultValue() }

@RequiresApi(18)
inline operator fun SparseLongArray.set(key: Int, value: Long) = put(key, value)
@RequiresApi(18)
inline operator fun SparseLongArray.contains(key: Int): Boolean = indexOfKey(key) >= 0


@RequiresApi(18)
operator fun SparseLongArray.plus(other: SparseLongArray): SparseLongArray {
    val new = SparseLongArray(size() + other.size())
    new.putAll(this)
    new.putAll(other)
    return new
}


@RequiresApi(18)
fun SparseLongArray.putAll(other: SparseLongArray) = other.forEach(this::put)


// 当集合中key对应的条项为value时, 删除该条项
@RequiresApi(18)
fun SparseLongArray.remove(key: Int, value: Long): Boolean {
    val index = indexOfKey(key)
    if (index >= 0 && value == valueAt(index)) {
        delete(key)
        return true
    }
    return false
}


@RequiresApi(18)
inline fun SparseLongArray.forEach(action: (key: Int, value: Long) -> Unit) {
    for (index in 0 until size()) {
        action(keyAt(index), valueAt(index))
    }
}


@RequiresApi(18)
fun SparseLongArray.keyIterator(): IntIterator = object : IntIterator() {
    var index: Int = 0
    override fun hasNext(): Boolean = index < size()
    override fun nextInt(): Int = keyAt(index++)
}


@RequiresApi(18)
fun SparseLongArray.valueIterator(): LongIterator = object : LongIterator() {
    var index: Int = 0
    override fun hasNext(): Boolean = index < size()
    override fun nextLong(): Long = valueAt(index++)
}
