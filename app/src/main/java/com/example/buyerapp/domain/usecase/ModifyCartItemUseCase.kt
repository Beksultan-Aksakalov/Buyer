package com.example.buyerapp.domain.usecase

import com.example.buyerapp.core.framework.network.DataState
import com.example.buyerapp.core.framework.network.apiCall
import com.example.buyerapp.core.framework.usecase.DataStateUseCase
import com.example.buyerapp.domain.model.Cart
import com.example.buyerapp.domain.repository.CartRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class ModifyCartItemUseCase @Inject constructor(private val cartRepository: CartRepository):
    DataStateUseCase<ModifyCartItemUseCase.Params, Cart>() {

    data class Params(
        val amount: Int,
        val itemId: Int
    )
    override suspend fun FlowCollector<DataState<Cart>>.execute(params: Params) {
        val result = apiCall {
            cartRepository.modifyCartItem(params.amount, params.itemId)
            cartRepository.getCart()
        }
        emit(result)
    }
}