package fundamentals.classes

import com.hieuluu.kotlin.oop.advanced.fundamentals.classes_and_bbjects.InitOrderDemo
import org.junit.Rule
import java.io.PrintStream
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

class InitOrderDemoTest {

    @get:Rule
    val outContent: PrintStream = System.out

    @Test
    fun testInitOrder() {
        val instance = InitOrderDemo("John")

        assertEquals("First property: John", instance.firstProperty)
        assertEquals("Second property: John", instance.secondProperty)

        val expectedOutput = """
            First initializer block
            Second initializer block
            Secondary constructor John: null
        """.trimIndent()

        assertEquals(expectedOutput, outContent.toString())
    }

    @Test
    fun testInitOrderWithAddress() {
        val instance = InitOrderDemo("John", "123 Main St")

        assertEquals("First property: John", instance.firstProperty)
        assertEquals("Second property: John", instance.secondProperty)

        val expectedOutput = """
            First initializer block
            Second initializer block
            Secondary constructor John: 123 Main St
        """.trimIndent()

        assertEquals(expectedOutput, outContent.toString())
    }
}

class NormalClassTest {

    @Test
    fun testEqual() {
        val instance1 = InitOrderDemo("John", "123 Main St")
        val instance2 = InitOrderDemo("John", "123 Main St")
        println("instances: $instance1 - $instance2")
        println("hashcode: ${instance1.hashCode()} - ${instance2.hashCode()}")
        assertNotEquals(instance1, instance2)
        assertNotEquals(instance1.hashCode(), instance2.hashCode())
    }
}