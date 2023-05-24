package com.example.buyerapp.application.navigation

import androidx.navigation.NavController
import com.example.buyerapp.presenter.confirm_otp.ConfirmOtpType
import com.example.buyerapp.presenter.destinations.BarCodeScreenDestination
import com.example.buyerapp.presenter.destinations.ConfirmOtpScreenDestination
import com.example.buyerapp.presenter.destinations.HomeScreenDestination
import com.example.buyerapp.presenter.destinations.InputCellPhoneScreenDestination
import com.example.buyerapp.presenter.destinations.NewPinCodeScreenDestination
import com.example.buyerapp.presenter.destinations.OnBoardingScreenDestination
import com.example.buyerapp.presenter.destinations.PersonalInfoScreenDestination
import com.example.buyerapp.presenter.destinations.PinCodeScreenDestination
import com.example.buyerapp.presenter.destinations.ProductInfoScreenDestination
import com.ramcosta.composedestinations.navigation.navigate

class AppNavigationProvider(private val navController: NavController) : NavigationProvider {

    override fun navigateUp() {
        navController.navigateUp()
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

    override fun openPinCode() {
        navController.navigate(PinCodeScreenDestination)
    }

    override fun openNewPinCode(smsToken: String, cellphone: String, confirmOtpType: ConfirmOtpType) {
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

}