package com.example.buyerapp.application.navigation

import androidx.navigation.NavController
import com.example.buyerapp.presenter.confirm_otp.ConfirmOtpType
import com.example.buyerapp.presenter.destinations.BasketScreenDestination
import com.example.buyerapp.presenter.destinations.ConfirmOtpScreenDestination
import com.example.buyerapp.presenter.destinations.DirectionDestination
import com.example.buyerapp.presenter.destinations.HomeScreenDestination
import com.example.buyerapp.presenter.destinations.InputCellPhoneScreenDestination
import com.example.buyerapp.presenter.destinations.NewPinCodeScreenDestination
import com.example.buyerapp.presenter.destinations.OnBoardingScreenDestination
import com.example.buyerapp.presenter.destinations.OrderHistoryScreenDestination
import com.example.buyerapp.presenter.destinations.PersonalInfoScreenDestination
import com.example.buyerapp.presenter.destinations.PinCodeScreenDestination
import com.example.buyerapp.presenter.destinations.ProductInfoScreenDestination
import com.example.buyerapp.presenter.destinations.ShopScreenDestination
import com.example.buyerapp.presenter.pincode.PinCodeScreenMode
import com.ramcosta.composedestinations.navigation.navigate

class AppNavigationProvider(private val navController: NavController) : NavigationProvider {

    override fun navigateUp() {
        navController.navigateUp()
    }

    override fun navigateUp(destination: DirectionDestination) {
        navController.popBackStack(destination.route, false)
    }

    override fun openOnBoarding() {
        navController.navigate(
            OnBoardingScreenDestination
        ) {
            popUpTo(navController.graph.id)
        }
    }

    override fun openInputCellPhone() {
        navController.navigate(InputCellPhoneScreenDestination)
    }

    override fun openConfirmOtp(cellphone: String, confirmOtpType: ConfirmOtpType) {
        navController.navigate(ConfirmOtpScreenDestination(confirmOtpType, cellphone))
    }

    override fun openPinCode(pinCodeScreenMode: PinCodeScreenMode, oldPin: String?) {
        navController.navigate(PinCodeScreenDestination(pinCodeScreenMode, oldPin))
    }

    override fun openNewPinCode(
        smsToken: String,
        cellphone: String,
        confirmOtpType: ConfirmOtpType
    ) {
        navController.navigate(NewPinCodeScreenDestination(confirmOtpType, smsToken, cellphone))
    }

    override fun openHome() {
        navController.navigate(
            HomeScreenDestination
        ) {
            popUpTo(navController.graph.id)
        }
    }

    override fun openPersonalInfo(firstname: String, lastname: String, cellphone: String) {
        navController.navigate(PersonalInfoScreenDestination(firstname, lastname, cellphone))
    }

    override fun openProductInfo(barcode: String) {
        navController.navigate(ProductInfoScreenDestination(barcode))
    }

    override fun openBasket() {
        navController.navigate(BasketScreenDestination)
    }

    override fun openShop() {
        navController.navigate(ShopScreenDestination)
    }

    override fun openOrderHistory() {
        navController.navigate(OrderHistoryScreenDestination)
    }

}