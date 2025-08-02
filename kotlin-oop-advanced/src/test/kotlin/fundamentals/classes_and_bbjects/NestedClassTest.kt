package fundamentals.classes_and_bbjects

import com.hieuluu.kotlin.oop.advanced.fundamentals.classes_and_bbjects.Outer
import org.junit.Assert.*
import kotlin.test.Test

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

import org.junit.Assert.*
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class NestedClassTest {
    @Test
    fun testNestedClass() {
        // Có thể tạo instance của Nested class mà không cần Outer
        val nested = Outer.Nested()
        nested.accessOuter()
    }

    @Test
    fun testInnerClass() {
        // Phải tạo instance của Outer trước khi tạo Inner
        val outer = Outer()
        val inner = outer.Inner()
        inner.accessOuter()
    }

    @Test
    fun testInnerClassMessageContent() {
        // Capture system output
        val outContent = ByteArrayOutputStream()
        val originalOut = System.out
        System.setOut(PrintStream(outContent))

        try {
            // Create Outer and Inner instances
            val outer = Outer()
            val inner = outer.Inner()

            // Call accessOuter and capture output
            inner.accessOuter()

            // Assert the output matches expected message
            val expectedMessage = "Thông điệp từ lớp cha: Tôi là thuộc tính của lớp cha\n"
            assertEquals(expectedMessage, outContent.toString())
        } finally {
            // Restore original system output
            System.setOut(originalOut)
        }
    }
}