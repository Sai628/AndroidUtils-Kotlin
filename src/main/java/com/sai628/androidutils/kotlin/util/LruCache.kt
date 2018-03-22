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

package com.sai628.androidutils.kotlin.util

import android.util.LruCache


inline fun <K: Any, V: Any> lruCache(
        maxSize: Int,
        crossinline sizeOf: (key: K, value: V) -> Int = { _, _ -> 1 },
        @Suppress("USELESS_CAST")
        crossinline create: (key: K) -> V? = { null as V? },
        crossinline onEntryRemoved: (evicted: Boolean, key: K, oldValue: V, newValue: V?) -> Unit =
                { _, _, _, _ -> }
): LruCache<K, V> {
    return object : LruCache<K, V>(maxSize) {
        override fun sizeOf(key: K, value: V) = sizeOf(key, value)
        override fun create(key: K) = create(key)
        override fun entryRemoved(evicted: Boolean, key: K, oldValue: V, newValue: V) {
            onEntryRemoved(evicted, key, oldValue, newValue)
        }
    }
}