package com.hieuluu.kotlin.oop.advanced.solid

/**
 * Nguyên tắc:
 * Mỗi lớp chỉ nên có một lý do duy nhất để thay đổi, tức là chỉ nên có một nhiệm vụ duy nhất.
 * */
// Violate  SRP
class UserViolateSRP(val name: String, val email: String) {
    fun saveToDatabase() {
    }

    fun sendEmail(message: String) {
    }
}

// Comply with SRP
class UserClassComplySRP(val name: String, val email: String)

class UserRepository {
    fun saveToDatabase(user: UserClassComplySRP) {
        // Lưu user vào database
    }
}

class EmailService {
    fun sendEmail(user: UserClassComplySRP, message: String) {
        // Gửi email cho user
    }
}
