package com.example.onboardingcompose.util

import androidx.annotation.DrawableRes
import com.example.onboardingcompose.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
        image = R.drawable.onboarding1,
        title = "Invest your money!",
        description = "Investa offers a dynamic platform that empowers you to invest and grow your money with ease."
    )

    object Second : OnBoardingPage(
        image = R.drawable.onboarding2,
        title = "Stay up-to date!",
        description = "Investa offers valuable insights and up-to-date news tailored specificaly for our investors. empowering you with market knowledge and new investment opportunities."
    )

    object Third : OnBoardingPage(
        image = R.drawable.onboarding3,
        title = "Track your progress!",
        description = "Our platform tracks your investments which is a crucial aspect of successful investing. it allows you to monitor the performance of your investments, make informed decisions, and adjust your portfolio as needed."
    )

    object Fourth : OnBoardingPage(
        image = R.drawable.onboarding4,
        title = "Join now for free!",
        description = "Start growing your money."
    )

}
