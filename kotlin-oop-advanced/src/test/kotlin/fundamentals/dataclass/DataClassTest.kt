package fundamentals.dataclass

import com.hieuluu.kotlin.oop.advanced.fundamentals.classes_and_bbjects.Person
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

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

class DataClassTest {

    @Test
    fun testEquals() {
        val user1 = Person("John")
        user1.age = 10
        val user2 = Person("John")
        user2.age = 20
        println("instances: $user1 - $user2")
        println("hashcode: ${user1.hashCode()} - ${user2.hashCode()}")
        // assert equal result true because the property [age] is excluded from generated functions
        assertEquals(user1, user2)
    }

    @Test
    fun testCopy() {
        val user1 = Person("John")
        user1.age = 10
        val user2 = user1.copy()
        assertEquals(user1, user2)
    }

    @Test
    fun testToString() {
        val user = Person("John")
        user.age = 10
        // assertEquals result true because the property [age] is excluded from generated functions
        assertEquals("Person(name=John)", user.toString())
        assertNotEquals("Person(name=John, age=10)", user.toString())
    }

    @Test
    fun testHashCode() {
        val user1 = Person("John")
        user1.age = 10
        val user2 = Person("John")
        user2.age = 20
        println(user1.hashCode().toString() + " " + user2.hashCode().toString())
        // assert equal result true because the property [age] is excluded from generated functions
        assertEquals(user1.hashCode(), user2.hashCode())
    }

    @Test
    fun testComponent1() {
        val user = Person("John")
        user.age = 10
        // assertEquals result true because the property [age] is excluded from generated functions
        assertEquals("John", user.component1())
    }
}