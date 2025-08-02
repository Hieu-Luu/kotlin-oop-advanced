package com.hieuluu.kotlin.oop.advanced.solid

/**
 * Nguyên tắc:
 * Các đối tượng của một lớp con phải có thể thay thế cho các đối tượng của lớp cha mà không làm thay đổi tính đúng đắn của chương trình.
 * */
// Violate LSP
open class Bird {
    open fun fly() {
        println("Flying")
    }
}

class Ostrich : Bird() {
    // Ostriches can't fly. Any object or method that require an instance of Bird and when we give an
    // Ostrich object, it will throw exception. In this case, the parent class Bird cannot be replaced by its child clas, the Ostrich.
    override fun fly() {
        throw UnsupportedOperationException("Ostrich can't fly")
    }
}

// Comply with LSP

/**
 * Instead define a specific bird's action here like 'fly' change it to general moving logic,
 * and override this action in classes which extend [BirdComplyLSP].
 */
open class BirdComplyLSP {
    open fun move() {
        println("Moving")
    }
}

class FlyingBird : BirdComplyLSP() {
    override fun move() {
        println("Flying")
    }
}

class OstrichComplyLSP : BirdComplyLSP() {
    override fun move() {
        println("Running")
    }
}