package fundamentals.flow

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
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

class FlowTest {

    // Kết hợp hai flow bằng cách ghép từng cặp phần tử tương ứng lại với nhau.
    // Flow kết quả có kích thước bằng với flow nhỏ hơn trong hai flow gốc.
    @Test
    fun testFlowZip(): Unit = runBlocking {
        val flow1 = flowOf(1, 2, 3, 4)
        val flow2 = flowOf("A", "B", "C")
        flow1.zip(flow2) { a, b -> "$a -> $b" }.collect {
            println(it)
        }

    }

    // Kết hợp 2 phần tử mới nhất của 2 flow khi một trong hai flow emit giá trị
    @Test
    fun testFlowCombine(): Unit = runBlocking {
        val flow1 = flowOf(1, 2, 3, 4)
        val flow2 = flowOf("A", "B", "C")
        flow1.combine(flow2) { a, b -> "$a -> $b" }.collect {
            println(it)
        }
    }

    @OptIn(FlowPreview::class)
    @Test
    fun testFlattenMerge(): Unit = runBlocking {
        val flow1 = flow {
            var i = 1
            while (i< 5) {
                emit(i++)
                delay(1000)
            }
        }
        val flow2 = flow {
            emit("A")
            delay(2000)
            emit("B")
            delay(2000)
            emit("C")
        }
        listOf(flow1, flow2).asFlow().flattenMerge().collect {
            println(it)
        }
    }

    @OptIn(FlowPreview::class)
    @Test
    fun testFlattenConcat(): Unit = runBlocking {
        val flow1 = flow {
            var i = 1
            while (i< 5) {
                emit(i++)
                delay(1000)
            }
        }
        val flow2 = flow {
            emit("A")
            delay(2000)
            emit("B")
            delay(2000)
            emit("C")
        }
        listOf(flow1, flow2).asFlow().flattenConcat().collect {
            println(it)
        }
    }

    // Result : print A, B, C  4 times
    @OptIn(FlowPreview::class)
    @Test
    fun testFlatMapConcat(): Unit = runBlocking {
        val flow1 = flow {
            var i = 1
            while (i< 5) {
                emit(i++)
                delay(1000)
            }
        }
        val flow2 = flow {
            emit("A")
            delay(2000)
            emit("B")
            delay(2000)
            emit("C")
        }
        flow1.flatMapConcat { flow2 }.collect {
            println(it)
        }
    }

    @OptIn(FlowPreview::class)
    @Test
    fun testFlatMapMerge(): Unit = runBlocking {
        val flow1 = flow {
            var i = 1
            while (i< 5) {
                emit(i++)
                delay(1000)
            }
        }
        val flow2 = flow {
            emit("A")
            delay(2000)
            emit("B")
            delay(2000)
            emit("C")
        }
        flow1.flatMapMerge { flow2 }.collect {
            println(it)
        }
    }


    @OptIn(FlowPreview::class)
    @Test
    fun `test Zip Flows`(): Unit = runBlocking {
        val flow1 = flow {
            emit("Loading")
            delay(200)
            emit("Success")
        }

        val flow2 = flow {
            delay(100)
            emit("Loading")
            delay(200)
            emit("Success")
        }

        flow1.zip(flow2) { a, b ->
            "$a -> $b"
        }
            .onStart { emit("Loading") }
            .collect {
                println(it)
            }
    }

    @OptIn(FlowPreview::class)
    @Test
    fun `test Zip Flows when alll Success`(): Unit = runBlocking {
        val flow1 = flow {
            emit("Loading")
            delay(200)
            emit("Success")
        }

        val flow2 = flow {
            delay(100)
            emit("Loading")
            delay(200)
            emit("Success")
        }

        flow1.zip(flow2) { a, b ->
            if (a == "Error") {
                return@zip a
            }

            if (b == "Error") {
                return@zip b
            }

            if (a == "Success" && b == "Success") {
                return@zip "$a -> $b"
            }

            return@zip "Loading"
        }
            .onStart { emit("Loading") }
            .collect {
                println(it)
            }
    }

    @OptIn(FlowPreview::class)
    @Test
    fun `test Zip Flows when flow 1 fail`(): Unit = runBlocking {
        val flow1 = flow {
            emit("Loading")
            delay(200)
            emit("Error")
        }

        val flow2 = flow {
            delay(100)
            emit("Loading")
            delay(200)
            emit("Success")
        }

        flow1.zip(flow2) { a, b ->
            if (a == "Error") {
                return@zip "$a - 1"
            }

            if (b == "Error") {
                return@zip b
            }

            if (a == "Success" && b == "Success") {
                return@zip "$a -> $b"
            }

            return@zip "Loading"
        }
            .onStart { emit("Loading") }
            .collect {
                println(it)
            }
    }


    @OptIn(FlowPreview::class)
    @Test
    fun `test Zip Flows when flow 2 fail`(): Unit = runBlocking {
        val flow1 = flow {
            emit("Loading")
            delay(200)
            emit("Success")
        }

        val flow2 = flow {
            delay(100)
            emit("Loading")
            delay(200)
            emit("Error")
        }

        flow1.zip(flow2) { a, b ->
            if (a == "Error") {
                return@zip a
            }

            if (b == "Error") {
                return@zip "$b - 2"
            }

            if (a == "Success" && b == "Success") {
                return@zip "$a -> $b"
            }

            return@zip "Loading"
        }
            .onStart { emit("Loading") }
            .collect {
                println(it)
            }
    }

    @OptIn(FlowPreview::class)
    @Test
    fun `test Merge Flows when all flow success`(): Unit = runBlocking {
        val flow1 = flow {
            emit("Loading")
            delay(200)
            emit("Success")
        }

        val flow2 = flow {
            delay(100)
            emit("Loading")
            delay(200)
            emit("Success")
        }

        merge(flow1, flow2)
            .collect {
                println(it)
            }
    }


    @OptIn(FlowPreview::class)
    @Test
    fun `test Combine Flows when all flow success`(): Unit = runBlocking {
        val flow1 = flow {
            emit("Loading")
            delay(200)
            emit("Success")
        }

        val flow2 = flow {
            delay(100)
            emit("Loading")
            delay(200)
            emit("Success")
        }

        combine(flow1, flow2) { a, b ->
            "$a -> $b"
        }
            .onStart { emit("Loading") }
            .collect {
                println(it)
            }
    }
}