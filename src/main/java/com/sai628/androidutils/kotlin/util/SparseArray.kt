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

import android.util.SparseArray


inline val <E> SparseArray<E>.size: Int get() = size()
inline fun <E> SparseArray<E>.isEmpty(): Boolean = size() == 0
inline fun <E> SparseArray<E>.isNotEmpty(): Boolean = size() != 0
inline fun <E> SparseArray<E>.containsKey(key: Int): Boolean = indexOfKey(key) >= 0
inline fun <E> SparseArray<E>.containsValue(value: E): Boolean = indexOfValue(value) != -1
inline fun <E> SparseArray<E>.getOrDefault(key: Int, defaultValue: E): E = get(key) ?: defaultValue
inline fun <E> SparseArray<E>.getOrElse(key: Int, defaultValue: () -> E): E = get(key) ?: defaultValue()

inline operator fun <E> SparseArray<E>.set(key: Int, value: E) = put(key, value)
inline operator fun <E> SparseArray<E>.contains(key: Int): Boolean = indexOfKey(key) >= 0


operator fun <E> SparseArray<E>.plus(other: SparseArray<E>): SparseArray<E> {
    val new = SparseArray<E>(size() + other.size())
    new.putAll(this)
    new.putAll(other)
    return new
}


fun <E> SparseArray<E>.putAll(other: SparseArray<E>) = other.forEach(this::put)


// 当集合中key对应的条项为value时, 删除该条项
fun <E> SparseArray<E>.remove(key: Int, value: E): Boolean {
    val index = indexOfKey(key)
    if (index >= 0 && value == valueAt(index)) {
        removeAt(index)
        return true
    }
    return false
}


inline fun <E> SparseArray<E>.forEach(action: (key: Int, value: E) -> Unit) {
    for (index in 0 until size()) {
        action(keyAt(index), valueAt(index))
    }
}


fun <E> SparseArray<E>.keyIterator(): IntIterator = object : IntIterator() {
    var index: Int = 0
    override fun hasNext(): Boolean = index < size()
    override fun nextInt(): Int = keyAt(index++)
}


fun <E> SparseArray<E>.valueIterator(): Iterator<E> = object : Iterator<E> {
    var index: Int = 0
    override fun hasNext(): Boolean = index < size()
    override fun next(): E = valueAt(index++)
}
