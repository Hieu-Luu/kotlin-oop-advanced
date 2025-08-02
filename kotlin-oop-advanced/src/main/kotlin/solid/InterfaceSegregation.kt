package com.hieuluu.kotlin.oop.advanced.solid
/**
 * Nhiều giao diện cụ thể tốt hơn là một giao diện tổng quát.
 * */
// Violate ISP
interface Worker {
    fun work()
    fun eat()
}

class Developer : Worker {
    override fun work() {
        // coding
    }

    override fun eat() {
        // eating
    }
}

class Robot : Worker {
    override fun work() {
        // working
    }

    override fun eat() {
        // Robot don't need to eat
        throw UnsupportedOperationException("Robot can't eat")
    }
}

// Comply with ISP

// Separate Worker interface to two interfaces Workable and Eatable
interface Workable {
    fun work()
}

interface Eatable {
    fun eat()
}

class Engineer : Workable, Eatable {
    override fun work() {
        // coding
    }

    override fun eat() {
        // eating
    }
}

class Droid : Workable {
    override fun work() {
        // working
    }
}