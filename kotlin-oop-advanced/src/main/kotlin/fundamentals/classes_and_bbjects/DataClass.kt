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
 * Compiler only use properties defined inside primary constructor for automatic generated functions.
 * In this case, property [age] is excluded from generated functions.
 * @see [data class](https://kotlinlang.org/docs/data-classes.html))
 **/
data class Person(val name: String) {
 var age: Int = 0
}