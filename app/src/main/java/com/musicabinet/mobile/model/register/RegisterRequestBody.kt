package com.musicabinet.mobile.model.register

/**
 * @author Kirchhoff-
 */
data class RegisterRequestBody(val email: String, val password: String, val fields: UserInfo)