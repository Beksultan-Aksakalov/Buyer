package com.example.buyerapp.presenter.store

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.buyerapp.application.navigation.NavigationProvider
import com.example.buyerapp.core.framework.extension.collectInLaunchedEffect
import com.example.buyerapp.core.framework.mvi.BaseEffect
import com.example.buyerapp.core.widget.LoadingView
import com.example.buyerapp.core.widget.SurfaceTopToolBarBack
import com.example.buyerapp.presenter.home.HomeTabsDestination
import com.example.buyerapp.presenter.store.view.StoreScreenContent
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun StoreScreen(
    id: Int,
    viewModel: StoreViewModel = hiltViewModel(),
    navigator: NavigationProvider
) {

    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(StoreEvent.LoadDetails(id))
    })

    if (uiState.isLoading) {
        LoadingView()
    } else {
        uiState.state?.let {
            SurfaceTopToolBarBack(
                onOnclickBackButton = { navigator.navigateUp() }
            ) {
                StoreScreenContent(
                    it.storeDetails,
                    onChangedStore = {
                        viewModel.onTriggerEvent(StoreEvent.ChooseStore)
                    },
                    onClickBarCode = {
                        navigator.openHome(HomeTabsDestination.Barcode)
                    },
                    onCheckedNotify = {
                        viewModel.onTriggerEvent(StoreEvent.ChangeNotifyState(it, id))
                    },
                    viewModel.imageLoader
                )
            }
        }
    }

    viewModel.effect.collectInLaunchedEffect { effect ->
        when (effect) {
            is BaseEffect.OnError -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT)
                .show()

            is StoreEffect.OpenHome -> navigator.openHome(HomeTabsDestination.Main)
        }
    }
}