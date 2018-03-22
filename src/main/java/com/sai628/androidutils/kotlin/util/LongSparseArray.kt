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

import android.util.LongSparseArray


inline val <E> LongSparseArray<E>.size: Int get() = size()
inline fun <E> LongSparseArray<E>.isEmpty(): Boolean = size() == 0
inline fun <E> LongSparseArray<E>.isNotEmpty(): Boolean = size() != 0
inline fun <E> LongSparseArray<E>.containsKey(key: Long): Boolean = indexOfKey(key) >= 0
inline fun <E> LongSparseArray<E>.containsValue(value: E): Boolean = indexOfValue(value) != -1
inline fun <E> LongSparseArray<E>.getOrDefault(key: Long, defaultValue: E): E = get(key) ?: defaultValue
inline fun <E> LongSparseArray<E>.getOrElse(key: Long, defaultValue: () -> E): E = get(key) ?: defaultValue()

inline operator fun <E> LongSparseArray<E>.set(key: Long, value: E) = put(key, value)
inline operator fun <E> LongSparseArray<E>.contains(key: Long) = indexOfKey(key) >= 0


operator fun <E> LongSparseArray<E>.plus(other: LongSparseArray<E>): LongSparseArray<E> {
    val new = LongSparseArray<E>(size() + other.size())
    new.putAll(this)
    new.putAll(other)
    return new
}


fun <E> LongSparseArray<E>.putAll(other: LongSparseArray<E>) = other.forEach(this::put)


// 当集合中key对应的条项为value时, 删除该条项
fun <E> LongSparseArray<E>.remove(key: Long, value: E): Boolean {
    val index = indexOfKey(key)
    if (index >= 0 && value == valueAt(index)) {
        removeAt(index)
        return true
    }
    return false
}


inline fun <E> LongSparseArray<E>.forEach(action: (key: Long, value: E) -> Unit) {
    for (index in 0 until size()) {
        action(keyAt(index), valueAt(index))
    }
}


fun <E> LongSparseArray<E>.keyIterator(): LongIterator = object : LongIterator() {
    var index: Int = 0
    override fun hasNext(): Boolean = index < size()
    override fun nextLong(): Long = keyAt(index++)
}


fun <E> LongSparseArray<E>.valueIterator(): Iterator<E> = object : Iterator<E> {
    var index: Int = 0
    override fun hasNext(): Boolean = index < size()
    override fun next(): E = valueAt(index++)
}
