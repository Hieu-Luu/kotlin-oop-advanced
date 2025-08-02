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

fun main() {
    val childClass = MyChildClass()
    val parentClass = MyParentClass()
//    private val privateClass = PrivateClass() // Compile error: Cannot access 'PrivateClass': it is private in file
    val secondPrivateClass = SecondPrivateClass()
    secondPrivateClass.accessPrivateMember()
    secondPrivateClass.accessPublicMember()
    secondPrivateClass.accessInternalMember()


    parentClass.accessPrivateMember()
    parentClass.accessProtectedMember()

    childClass.accessPrivateMember()
    childClass.accessProtectedMember()
}

// Sửa lại: protected chỉ dùng cho member của class kế thừa, không dùng cho class thường
private class SecondPrivateClass {
    val publicProperty: String = "Public Property in SecondPrivateClass"
    private val privateProperty: String = "Private Property in SecondPrivateClass"
    internal val internalProperty: String = "Internal Property in SecondPrivateClass"

    fun accessPrivateMember() {
        println("Accessing: $privateProperty")
    }

    fun accessPublicMember() {
        println("Accessing: $publicProperty")
    }

    fun accessInternalMember() {
        println("Accessing: $internalProperty")
    }
}