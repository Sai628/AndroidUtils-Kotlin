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

package com.sai628.androidutils.kotlin.os

import android.os.Build
import android.os.PersistableBundle
import android.support.annotation.RequiresApi


@RequiresApi(21)
fun persistableBundleOf(vararg pairs: Pair<String, Any?>): PersistableBundle = PersistableBundle(pairs.size).apply {
    for ((key, value) in pairs) {
        when (value) {
            // null值
            null -> putString(key, null)

            // 标量
            is Boolean -> {
                if (Build.VERSION.SDK_INT >= 22) {
                    putBoolean(key, value)
                } else {
                    throw IllegalArgumentException("Illegal value type boolean for key \"$key\"")
                }
            }
            is Double -> putDouble(key, value)
            is Int -> putInt(key, value)
            is Long -> putLong(key, value)

            // 引用
            is String -> putString(key, value)

            // 标量数组
            is BooleanArray -> {
                if (Build.VERSION.SDK_INT >= 22) {
                    putBooleanArray(key, value)
                } else {
                    throw IllegalArgumentException("Illegal value type boolean[] for key \"$key\"")
                }
            }
            is DoubleArray -> putDoubleArray(key, value)
            is IntArray -> putIntArray(key, value)
            is LongArray -> putLongArray(key, value)

            // 引用数组
            is Array<*> -> {
                val componentType = value::class.java.componentType

                @Suppress("UNCHECKED_CAST")
                when {
                    String::class.java.isAssignableFrom(componentType) -> {
                        putStringArray(key, value as Array<String>)
                    }
                    else -> {
                        val valueType = componentType.canonicalName
                        throw IllegalArgumentException("Illegal value array type $valueType for key \"$key\"")
                    }
                }
            }

            else -> {
                val valueType = value.javaClass.canonicalName
                throw IllegalArgumentException("Illegal value type $valueType for key \"$key\"")
            }
        }
    }
}
