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

import android.util.SparseBooleanArray


inline val SparseBooleanArray.size: Int get() = size()
inline fun SparseBooleanArray.isEmpty(): Boolean = size() == 0
inline fun SparseBooleanArray.isNotEmpty(): Boolean = size() != 0
inline fun SparseBooleanArray.containsKey(key: Int): Boolean = indexOfKey(key) >= 0
inline fun SparseBooleanArray.containsValue(value: Boolean): Boolean = indexOfValue(value) != -1
inline fun SparseBooleanArray.getOrDefault(key: Int, defaultValue: Boolean): Boolean = get(key, defaultValue)
inline fun SparseBooleanArray.getOrElse(key: Int, defaultValue: () -> Boolean): Boolean =
        indexOfKey(key).let { if (it != -1) valueAt(it) else defaultValue() }

inline operator fun SparseBooleanArray.set(key: Int, value: Boolean) = put(key, value)
inline operator fun SparseBooleanArray.contains(key: Int): Boolean = indexOfKey(key) >= 0


operator fun SparseBooleanArray.plus(other: SparseBooleanArray): SparseBooleanArray {
    val new = SparseBooleanArray(size() + other.size())
    new.putAll(this)
    new.putAll(other)
    return new
}


fun SparseBooleanArray.putAll(other: SparseBooleanArray) = other.forEach(this::put)


// 当集合中key对应的条项为value时, 删除该条项
fun SparseBooleanArray.remove(key: Int, value: Boolean): Boolean {
    val index = indexOfKey(key)
    if (index >= 0 && value == valueAt(index)) {
        delete(key)
        return true
    }
    return false
}


inline fun SparseBooleanArray.forEach(action: (key: Int, value: Boolean) -> Unit) {
    for (index in 0 until size()) {
        action(keyAt(index), valueAt(index))
    }
}


fun SparseBooleanArray.keyIterator(): IntIterator = object : IntIterator() {
    var index: Int = 0
    override fun hasNext(): Boolean = index < size()
    override fun nextInt(): Int = keyAt(index++)
}


fun SparseBooleanArray.valueIterator(): BooleanIterator = object : BooleanIterator() {
    var index: Int = 0
    override fun hasNext(): Boolean = index < size()
    override fun nextBoolean(): Boolean = valueAt(index++)
}
