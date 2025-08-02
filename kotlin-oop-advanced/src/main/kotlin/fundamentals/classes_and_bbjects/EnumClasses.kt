package com.hieuluu.kotlin.oop.advanced.fundamentals.classes_and_bbjects

import java.util.function.BinaryOperator
import java.util.function.IntBinaryOperator
import kotlin.enums.enumEntries

/*
 * Copyright 2024 Hieu Luu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// An example of Protocol Sate
enum class EnumWithAnonymousClass {
    WAITING {
        override fun signal(): EnumWithAnonymousClass = TALKING
    },

    TALKING {
        override fun signal(): EnumWithAnonymousClass = WAITING
    };

    abstract fun signal(): EnumWithAnonymousClass
}

enum class EnumImplInterface : BinaryOperator<Int>, IntBinaryOperator {
    PLUS {
        override fun apply(t: Int, u: Int): Int = t + u
    },
    TIMES {
        override fun apply(t: Int, u: Int): Int = t - u
    };

    override fun applyAsInt(t: Int, u: Int): Int = apply(t, u)
}

//enum class EnumClass : SealedInterface {
//    WORK {
//        override fun
//                work() {
//        }
//
//        override fun eat() {}
//    },
//    EAT {
//        override fun work() {}
//        override fun eat() {}
//    }
//}

enum class EnumConstants { RED, GREEN, BLUE }

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T : Enum<T>> allValuesToString(): String =
    enumEntries<T>().joinToString { it.name }