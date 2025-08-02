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

class Outer {
    companion object {
        const val StaticProperty = "Static property"
    }

    var backingField: String = ""
        get() = field.uppercase() // Sử dụng field để truy cập giá trị backing field
        set(value) {
            field = "Backing field value: $value" // Gán giá trị mới vào backing field
        }
    private var _backingProperty: String = "Backing property"
    val backingProperty: String
        get() = _backingProperty
        
    val publicProperty: String = "Public property"
    private val privateProperty: String = "Private property"
    protected val protectedProperty: String = "Protected property"
    internal val internalProperty: String = "Internal property"

    fun setBackingProperty(value: String) {
        _backingProperty = value
        println("Update Backing property value: $_backingProperty")
    }

    class Nested {
        fun accessOuter() {
            println("Accessing outer class properties:")
        
            // println(publicProperty) // Compile error: Cannot access 'publicProperty': it is private in file
            // println(privateProperty) // Compile error: Cannot access 'privateProperty': it is private in file
            // println(protectedProperty) // Compile error: Cannot access 'protectedProperty': it is private in file
            // println(internalProperty) // Compile error: Cannot access 'internalProperty': it is private in file
            println("Accessing static property: ${StaticProperty}")
        }
    }

    inner class Inner {
        fun accessOuter() {
            println("Accessing outer class properties from inner class:")
            println("Public property: $publicProperty")
            println("Private property: $privateProperty")
            println("Protected property: $protectedProperty")
            println("Internal property: $internalProperty")
            println("Backing field: $backingField")
            println("Accessing static property: ${StaticProperty}")
        }
    }
}

fun main() {
    val outer = Outer()
    outer.backingField = "Hieu Luu"
    println(outer.backingField)

    outer.setBackingProperty("Hieu Luu")

    println("To initialize a nested class don't need an outer class instance")
    Outer.Nested().accessOuter()

    println("To initialize an inner class need an outer class instance")
    outer.Inner().accessOuter()
}