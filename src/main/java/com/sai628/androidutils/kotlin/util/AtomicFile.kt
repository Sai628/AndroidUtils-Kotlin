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
import android.util.AtomicFile
import java.io.FileOutputStream
import java.nio.charset.Charset


@RequiresApi(17)
inline fun AtomicFile.tryWrite(block: (out: FileOutputStream) -> Unit) {
    val stream = startWrite()
    var success = false
    try {
        block(stream)
        success = true
    } finally {
        if (success) {
            finishWrite(stream)
        } else {
            failWrite(stream)
        }
    }
}


@RequiresApi(17)
fun AtomicFile.writeBytes(array: ByteArray) {
    tryWrite { it.write(array) }
}


@RequiresApi(17)
fun AtomicFile.writeText(text: String, charset: Charset = Charsets.UTF_8) {
    writeBytes(text.toByteArray(charset))
}


@RequiresApi(17)
inline fun AtomicFile.readBytes(): ByteArray = readFully()


@RequiresApi(17)
fun AtomicFile.readText(charset: Charset = Charsets.UTF_8): String {
    return readFully().toString(charset)
}
