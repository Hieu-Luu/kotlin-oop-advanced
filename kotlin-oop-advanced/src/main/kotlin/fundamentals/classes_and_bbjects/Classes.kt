package com.hieuluu.kotlin.oop.advanced.fundamentals.classes_and_bbjects

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

/**
 * @see [https://kotlinlang.org/docs/classes.html](https://kotlinlang.org/docs/classes.html)
 * The initializer blocks are executed in the same order as they appear in the class body
 **/
class InitOrderDemo(name: String) {
    constructor(name: String, address: String) : this(name) {
        println("Secondary constructor $name: $address")
    }

    val firstProperty = "First property: $name".also(::println)

    init {
        println("First initializer block")
    }

    val secondProperty = "Second property: $name".also(::println)

    init {
        println("Second initializer block")
    }
}

// Only can access when in the same file with PrivateClass
private class PrivateClass {

}

open class MyParentClass {
    val publicProperty: String = "Public Property"
    private val privateProperty: String = "Private Property"
    protected val protectedProperty: String = "Protected Property"
    internal val internalProperty: String = "Internal Property"

    fun accessPublicMember() {
        println("Access: $publicProperty")
    }

    fun accessPrivateMember() {
        println("Access: $privateProperty") // Có thể truy cập từ bên trong lớp
    }

    fun accessProtectedMember() {
        println("Access: $protectedProperty")
    }

    fun accessInternalMember() {
        println("Access:  $internalProperty")
    }

    internal class InternalClass {

    }
}

class MyChildClass : MyParentClass() {
    fun accessProtectedProperty() {
        println("Child class access: $protectedProperty")
    }
}