package com.example.buyerapp.presenter.new_pincode

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.buyerapp.core.framework.extension.collectInLaunchedEffect
import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.application.navigation.NavigationProvider
import com.example.buyerapp.core.widget.LoadingView
import com.example.buyerapp.core.widget.SurfaceTopToolBarBack
import com.example.buyerapp.presenter.confirm_otp.ConfirmOtpType
import com.example.buyerapp.presenter.new_pincode.view.NewPinCodeContent
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun NewPinCodeScreen(
    confirmOtpType: ConfirmOtpType,
    smsToken: String,
    cellPhone: String,
    viewModel: NewPinCodeViewModal = hiltViewModel(),
    navigator: NavigationProvider
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    SurfaceTopToolBarBack(
        onOnclickBackButton = { navigator.navigateUp() }
    ) {
        if (uiState.isLoading) {
            LoadingView()
        } else {
            NewPinCodeContent(
                onInputPinComplete = {
                    when(confirmOtpType){
                        ConfirmOtpType.AUTH_COMPLETE ->
                            viewModel.onTriggerEvent(NewPinCodeEvent.Complete(smsToken, cellPhone, it))
                        ConfirmOtpType.PIN_RESET ->
                            viewModel.onTriggerEvent(NewPinCodeEvent.ResetConfirm(smsToken, it))
                    }

                }
            )
        }
    }

    viewModel.effect.collectInLaunchedEffect { effect ->
        when (effect) {
            is BaseEffect.OnError -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT)
                .show()

            is NewPinCodeEffect.OnNavigateHome -> navigator.openHome()
        }
    }
}