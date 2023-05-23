package com.example.buyerapp.data.repository

import com.example.buyerapp.data.network.ApiService
import com.example.buyerapp.data.network.dto.auth.AuthCompleteReq
import com.example.buyerapp.data.network.dto.auth.AuthStartReq
import com.example.buyerapp.data.network.dto.auth.toDomain
import com.example.buyerapp.domain.model.AuthComplete
import com.example.buyerapp.domain.repository.AuthBuyerRepository
import javax.inject.Inject

class AuthBuyerRepositoryImpl @Inject constructor(val apiService: ApiService) :
    AuthBuyerRepository {

    override suspend fun onSendPhoneToReceiveOtp(cellPhone: String) {
        return apiService.sendPhoneToReceiveOtp(AuthStartReq(cellPhone))
    }

    override suspend fun onComplete(
        smsToken: String,
        cellPhone: String,
        newPin: String
    ): AuthComplete {
        return apiService.onComplete(smsToken, AuthCompleteReq(cellPhone, newPin)).toDomain()
    }

    override suspend fun onLogout() {
        return apiService.authLogout()
    }

    override suspend fun pinCheck(pin: String) {
        TODO("Not yet implemented")
    }


}