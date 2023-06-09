package com.example.buyerapp.domain.repository

import com.example.buyerapp.domain.model.AuthComplete

interface AuthBuyerRepository {

    suspend fun onSendPhoneToReceiveOtp(cellPhone: String)

    suspend fun onComplete(smsToken: String, cellPhone: String, newPin: String): AuthComplete

    suspend fun pinCheck(pin: String)

    suspend fun pinChange(oldPin: String, newPin: String)

    suspend fun onLogout()

    suspend fun pinResetSms()

    suspend fun pinResetConfirm(pinOtp: String, pin: String)
}