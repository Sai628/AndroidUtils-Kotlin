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

import android.util.SparseIntArray


inline val SparseIntArray.size: Int get() = size()
inline fun SparseIntArray.isEmpty(): Boolean = size() == 0
inline fun SparseIntArray.isNotEmpty(): Boolean = size() != 0
inline fun SparseIntArray.containsKey(key: Int): Boolean = indexOfKey(key) >= 0
inline fun SparseIntArray.containsValue(value: Int): Boolean = indexOfValue(value) != -1
inline fun SparseIntArray.getOrDefault(key: Int, defaultValue: Int): Int = get(key, defaultValue)
inline fun SparseIntArray.getOrElse(key: Int, defaultValue: () -> Int): Int =
        indexOfKey(key).let { if (it != -1) valueAt(it) else defaultValue() }

inline operator fun SparseIntArray.set(key: Int, value: Int) = put(key, value)
inline operator fun SparseIntArray.contains(key: Int): Boolean = indexOfKey(key) >= 0


operator fun SparseIntArray.plus(other: SparseIntArray): SparseIntArray {
    val new = SparseIntArray(size() + other.size())
    new.putAll(this)
    new.putAll(other)
    return new
}


fun SparseIntArray.putAll(other: SparseIntArray) = other.forEach(this::put)


// 当集合中key对应的条项为value时, 删除该条项
fun SparseIntArray.remove(key: Int, value: Int): Boolean {
    val index = indexOfKey(key)
    if (index >= 0 && value == valueAt(index)) {
        removeAt(index)
        return true
    }
    return false
}


inline fun SparseIntArray.forEach(action: (key: Int, value: Int) -> Unit) {
    for (index in 0 until size()) {
        action(keyAt(index), valueAt(index))
    }
}


fun SparseIntArray.keyIterator(): IntIterator = object : IntIterator() {
    var index: Int = 0
    override fun hasNext(): Boolean = index < size()
    override fun nextInt(): Int = keyAt(index++)
}


fun SparseIntArray.valueIterator(): IntIterator = object : IntIterator() {
    var index: Int = 0
    override fun hasNext(): Boolean = index < size()
    override fun nextInt(): Int = valueAt(index++)
}
