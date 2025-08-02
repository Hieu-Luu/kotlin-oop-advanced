package com.hieuluu.kotlin.oop.advanced.solid

/**
 * Nguyên tắc:
 * Các thực thể phần mềm (lớp, module, hàm, v.v.) nên được mở để mở rộng nhưng đóng để sửa đổi.
 * */

// Violate OCP
class Rectangle(val width: Double, val height: Double)
class Circle(val radius: Double)

class AreaCalculator {
    fun calculateArea(shape: Any): Double {
        return when (shape) {
            is Rectangle -> shape.width * shape.height
            is Circle -> Math.PI * shape.radius * shape.radius
            else -> throw IllegalArgumentException("Shape not supported")
        }
    }
}

// Comply with OCP
interface Shape {
    fun calculateArea(): Double
}

class RectangleComplyOCR(private val width: Double, private val height: Double) : Shape {
    override fun calculateArea() = width * height
}

class CircleComplyOCR(private val radius: Double) : Shape {
    override fun calculateArea() = Math.PI * radius * radius
}

/**
 * When have new shape, the [AreaCalculatorComplyOCR] doesn't need tobe change source code of [calculateTotalArea] method.
 * Just need to create a class that implement [Shape] interface
 * */
class AreaCalculatorComplyOCR {

    fun printArea() {
        val shapes = listOf(
            RectangleComplyOCR(1.0, 2.0),
            CircleComplyOCR(3.0)
        )
        println("Area: ${calculateTotalArea(shapes)}")
    }

    private fun calculateTotalArea(shapes: List<Shape>): Double {
        var totalArea = 0.0
        for (shape in shapes) {
            totalArea += shape.calculateArea()
        }
        return totalArea
    }
}