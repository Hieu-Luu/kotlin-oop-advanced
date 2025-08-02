package com.hieuluu.kotlin.oop.advanced.solid

/**
 * Các module cấp cao không nên phụ thuộc vào các module cấp thấp, cả hai nên phụ thuộc vào các abstractions.
 * Các abstractions không nên phụ thuộc vào chi tiết, các chi tiết nên phụ thuộc vào abstractions.
 * */
// Violate DIP
class MySQLDatabase {
    fun connect() {
        // Connect to MySQL
    }
}

class UserRepo {
    private val db = MySQLDatabase()

    fun getUser(userId: Int) {
        db.connect()
        // get user logic
    }
}

// Tuân thủ DIP
interface Database {
    fun connect()
}

class MySQLDatabaseComplyDIP : Database {
    override fun connect() {
        // Connect to MySQL
    }
}

/**
 * The [UserRepoComplyDIP] depends on abstraction [Database] instead of a concrete class [MySQLDatabase]
 * */
class UserRepoComplyDIP(private val db: Database) {
    fun getUser(userId: Int) {
        db.connect()
        // get user logic
    }
}