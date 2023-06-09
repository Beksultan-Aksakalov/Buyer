package com.example.buyerapp.presenter.promo

import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
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
import com.example.buyerapp.presenter.promo.view.PromoItems
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun PromoScreen(
    storeId: Long,
    viewModel: PromoViewModel = hiltViewModel(),
    navigator: NavigationProvider
) {

    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(PromoEvent.LoadPromos(storeId))
    })


    if (uiState.isLoading) {
        LoadingView()
    } else {
        uiState.state?.let { state ->
            SurfaceTopToolBarBack(
                onOnclickBackButton = { navigator.navigateUp() },
                title = "Скидки",
                onShowCommonButton = true,
                onClickCommonButton = {
                    navigator.openHomeForFilterPromo()
                }
            ) {
                LazyColumn {
                    items(state.promos.data) { promo ->
                        PromoItems(promo = promo, imageLoader = viewModel.imageLoader) {

                        }
                    }
                }
            }
        }
    }

    viewModel.effect.collectInLaunchedEffect { effect ->
        when (effect) {
            is BaseEffect.OnError -> Toast.makeText(context, effect.message, Toast.LENGTH_SHORT)
                .show()
        }
    }
}