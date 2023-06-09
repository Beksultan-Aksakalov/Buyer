package com.example.buyerapp.presenter.onboarding

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.buyerapp.core.framework.extension.collectInLaunchedEffect
import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.application.navigation.NavigationProvider
import com.example.buyerapp.core.widget.LoadingView
import com.example.buyerapp.presenter.onboarding.view.OnBoardingContent
import com.example.buyerapp.presenter.pincode.PinCodeScreenMode
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel = hiltViewModel(),
    navigator: NavigationProvider
) {

    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(OnBoardingEvent.LoadItems)
    })

    Surface(modifier = Modifier.systemBarsPadding()) {
        OnBoardingBody {
            if (uiState.isLoading) {
                LoadingView()
            } else {
                uiState.state?.let {
                    OnBoardingContent(
                        data = it,
                        onSignIn = {
                            viewModel.onTriggerEvent(OnBoardingEvent.CheckAuthKey)
                        },
                        imageLoader = viewModel.imageLoader
                    )
                }
            }
        }
    }


    viewModel.effect.collectInLaunchedEffect { effect ->
        when (effect) {
            is BaseEffect.OnError -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT)
                .show()

            is OnBoardingEffect.OnNavigateToCellPhone -> navigator.openInputCellPhone()
            is OnBoardingEffect.OnNavigateToPinCode -> navigator.openPinCode(PinCodeScreenMode.LOGIN)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OnBoardingBody(
    pageContent: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        content = pageContent
    )
}