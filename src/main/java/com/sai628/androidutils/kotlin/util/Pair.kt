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

import android.util.Pair


inline operator fun <F, S> Pair<F, S>.component1(): F = first
inline operator fun <F, S> Pair<F, S>.component2(): S = second
inline fun <F, S> Pair<F, S>.toKotlinPair(): kotlin.Pair<F, S> = kotlin.Pair(first, second)
inline fun <F, S> kotlin.Pair<F, S>.toAndroidPair(): Pair<F, S> = Pair(first, second)
