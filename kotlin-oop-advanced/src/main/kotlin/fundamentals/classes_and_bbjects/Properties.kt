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

class Properties(name: String, address: String) {
    lateinit var lateInitValue: List<String>
    var backingField: String = name
        get() = field.uppercase() // Sử dụng field để truy cập giá trị backing field
        set(value) {
            if (value.isNotBlank()) {
                field = "Mr. " + value // Gán giá trị mới vào backing field
            }
        }

    private var _backingProperty: String = address
    var backingProperty: String
        get() = _backingProperty
        set(value) {
            _backingProperty = value.trim()
        }
}