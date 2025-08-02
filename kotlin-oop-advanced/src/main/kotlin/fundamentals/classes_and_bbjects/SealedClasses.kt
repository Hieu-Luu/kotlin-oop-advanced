package com.hieuluu.kotlin.oop.advanced.fundamentals.classes_and_bbjects

import java.io.File

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

/**
 * @see [Sealed class](https://kotlinlang.org/docs/sealed-classes.html)
 * 1. Sealed classes and sealed interfaces are used to restrict the inheritance hierarchy of a class:
 *  - A sealed class itself is always an abstract class, and as a result, CAN NOT be instantiated directly.
 *    However, it may contain or inherit constructors. These constructors aren't for creating instances of the sealed class itself but for its subclasses.
 *  - All direct subclasses are known at compile time.
 *  - Subclasses CAN NOT appear outside the module and package within the sealed class is defined.
 * 2. Same logic applies to interfaces and their implementation.
 * 3. Sealed classes scenarios:
 * - Limited class inheritance.
 * - Type-safe design is required: Safety and pattern matching are crucial in the project.
 *   Particularly for state management or handling complex conditional logic.
 * - Working with closed APIs
 *
 **/

// Inheritance: Sealed interface has implementation only in the same package and module
sealed interface Error

// Inheritance: This sealed class is extendable only within the same package.
sealed class SealedIOError : Error {
    // Constructor: constructor has protected visibility by default
    constructor()
    // Constructor: private constructor visible inside this class only
    private constructor(description: String): this()
    // This will raise error because public and internal constructors are not allowed in sealed classes
    // public constructor(code: Int): this()
}

// Inheritance: This class is extendable only within the same package.
class FileReadError(val file: File) : SealedIOError()
//Inheritance: This class is extendable only within the same package.
class FileWriteError(val file: File) : SealedIOError()
// Inheritance: This class can be extended anywhere it's visible.
open class CustomerError() : SealedIOError()

// Singleton object implementing the 'Error' sealed interface
// This will raise warning:'sealed' subclass has no state and no overridden 'equals()':
// class RuntimeError : Error
data object RuntimeError : Error

fun log(e: Error) = when (e) {
    is FileReadError -> println("File read error: ${e.file}")
    is FileWriteError -> println("File write error: ${e.file}")
    is CustomerError -> println("Customer error")
    is RuntimeError -> println("Runtime error")
    ErrorType.LOW -> println("Low severity error")
    ErrorType.MEDIUM -> println("Medium severity error")
    ErrorType.HIGH -> println("High severity error")
    // No `else` is required
}

/**
 * A sealed class itself is always an abstract class, and as a result, CAN NOT be instantiated directly.
 * However, it may contain or inherit constructors. These constructors aren't for creating instances of the sealed class itself but for its subclasses.
 **/
sealed class SealedError(val message: String) {
    class NetworkError : SealedError("Network error")
    class DatabaseError : SealedError("Database error")
    class UnknownError : SealedError("Unknown error")
}

/**
 * Enum with sealed class
 **/
enum class ErrorSeverity { LOW, MEDIUM, HIGH }
sealed class SealedErrorSeverity(val severity: ErrorSeverity) {
    class FileReadError(val file: File): SealedErrorSeverity(severity = ErrorSeverity.LOW)
    class DatabaseError(val source: String) : SealedErrorSeverity(severity = ErrorSeverity.MEDIUM)
    data object RuntimeError: SealedErrorSeverity(ErrorSeverity.HIGH)
}

/**
 * Enum CANNOT extend a sealed class or any other class. However, they can implement sealed interfaces
 * */
enum class ErrorType : Error {
    LOW, MEDIUM, HIGH
}

/**
 * ============= [Use case scenarios] =============
 * */

/**
 * [Use case 1]: State management UI application
 **/
sealed class UISate {
    data object Loading : UISate()
    data class Success(val message: String) : UISate()
    data class Error(val exception: Exception) : UISate()
}

fun updateUI(state: UISate) = when (state) {
    is UISate.Loading -> println("Loading...")
    is UISate.Success -> println("Success: ${state.message}")
    is UISate.Error -> println("Error: ${state.exception}")
}

/**
* [Use case 2]: Payment method handling
**/
sealed class Payment {
    data class Card(val cardNumber: String) : Payment()
    data class BankAccount(val accountNumber: String) : Payment()
    data object Cash : Payment()
}

fun processPayment(payment: Payment) = when (payment) {
    is Payment.Card -> println("Card: ${payment.cardNumber}")
    is Payment.BankAccount -> println("Bank account: ${payment.accountNumber}")
    is Payment.Cash -> println("Cash")
}

/**
 * [Use case 3]: API request-response handling
 **/
sealed interface ApiRequest
data class LoginRequest(val username: String, val password: String) : ApiRequest
data object LogoutRequest : ApiRequest

sealed interface ApiResponse {
    data class UserSuccess(val user: String): ApiResponse
    data object UserNotFound: ApiResponse
    data class Error(val message: String): ApiResponse
}

fun handleApiRequest(request: ApiRequest): ApiResponse = when (request) {
    is LoginRequest -> {
        val isValidUser = request.username == "admin"
        if (isValidUser) {
            ApiResponse.UserSuccess(request.username)
        } else {
            ApiResponse.Error("Invalid credentials")
        }
    }
    is LogoutRequest -> {
        ApiResponse.UserSuccess(user = "user name")
    }
}

fun getUserById(userId: String): ApiResponse = when (userId == "valid user id") {
    true -> ApiResponse.UserSuccess(userId)
    else -> ApiResponse.UserNotFound
}

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}