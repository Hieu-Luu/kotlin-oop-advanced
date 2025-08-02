package fundamentals.enumclass

import com.hieuluu.kotlin.oop.advanced.fundamentals.classes_and_bbjects.EnumConstants
import kotlin.test.Test
import kotlin.test.assertEquals

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

class EnumConstantsTest {

    @Test
    fun testEnumConstants() {
        // Test cases for EnumConstants
        assertEquals(EnumConstants.RED, EnumConstants.valueOf("RED"))
        assertEquals(EnumConstants.GREEN, EnumConstants.valueOf("GREEN"))
        assertEquals(EnumConstants.BLUE, EnumConstants.valueOf("BLUE"))
    }

    @Test
    fun getEntries() {
        val entries = EnumConstants.entries
        assertEquals(3, entries.size)
        assertEquals(EnumConstants.RED, entries[0])
        assertEquals(EnumConstants.GREEN, entries[1])
        assertEquals(EnumConstants.BLUE, entries[2])
    }
}