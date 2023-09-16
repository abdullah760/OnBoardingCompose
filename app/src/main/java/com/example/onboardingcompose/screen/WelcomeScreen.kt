package com.example.onboardingcompose.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onboardingcompose.navigation.Screen
import com.example.onboardingcompose.util.OnBoardingPage
import com.example.onboardingcompose.viewmodel.WelcomeViewModel
import com.google.accompanist.pager.*
import androidx.compose.ui.unit.sp




@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun WelcomeScreen(
    navController: NavHostController,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third,
        OnBoardingPage.Fourth
    )
    val pagerState = rememberPagerState()

    Column(modifier = Modifier.fillMaxSize()) {
        SkipButton(modifier = Modifier, pagerState = pagerState) {
            //welcomeViewModel.saveOnBoardingState(completed = false)
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
        }
        HorizontalPager(
            modifier = Modifier.weight(10f),
            count = 4,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { position ->
            PagerScreen(onBoardingPage = pages[position])
        }
//        HorizontalPagerIndicator(
//            modifier = Modifier
//                .align(Alignment.CenterHorizontally)
//                .weight(1f)
//                .background(Color(0xFF2796ff)),
//            pagerState = pagerState
//        )
        Box{
            FinishButton(
                modifier = Modifier
                    ,
                pagerState = pagerState
            ) {
//                welcomeViewModel.saveOnBoardingState(completed = false)
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
            }
            NextButton(modifier = Modifier, pagerState = pagerState){
                    
                }
            }
        }

    }


@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFF2796ff)),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Image(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.5f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = "Pager Image"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
//                .fillMaxHeight(0.2f)

                ,
            text = onBoardingPage.title,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.White
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 30.dp),
            text = onBoardingPage.description,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}


@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SkipButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF2796ff))
            .padding(horizontal = 5.dp)

        ,
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.End
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(0.2f),
            visible = pagerState.currentPage == 0 || pagerState.currentPage == 1 || pagerState.currentPage == 2
        ) {
            Button(

                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp
                ),
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White, backgroundColor = Color.Transparent
                ),
            ) {
                Text(text = "Skip",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,

                    )
            }
        }
    }
}
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun NextButton(
    modifier: Modifier,
    pagerState: PagerState,
    onNextClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .background(Color(0xFF2796ff))
            .padding(horizontal = 20.dp)
            .padding(vertical = 5.dp)
        ,
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == 0 || pagerState.currentPage == 1 || pagerState.currentPage == 2

        ) {
            Button(
                onClick = {
                    if (pagerState.currentPage < 3) {
                        onNextClicked()
                    } else {
                        // Handle onboarding completion
                    }
                },
                modifier = Modifier
                    .height(45.dp)
                    ,

                shape = CircleShape,


                colors = ButtonDefaults.buttonColors(
                    contentColor = Color(0xFF2796ff) , backgroundColor = Color.White
                )
            ) {
                Text(text = "Next",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp


                )
            }
        }
    }
}
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .background(Color(0xFF2796ff))
            .padding(horizontal = 20.dp)
            .padding(vertical = 5.dp)

        ,
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == 3
        ) {
            Button(
                modifier = Modifier
                    .height(45.dp)

                ,

                shape = CircleShape,
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color(0xFF2796ff) , backgroundColor = Color.White
                )
            ) {
                Text(text = "Finish",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FirstOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.First)
    }
}

@Composable
@Preview(showBackground = true)
fun SecondOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.Second)
    }
}

@Composable
@Preview(showBackground = true)
fun ThirdOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.Third)
    }
}

    @Composable
    @Preview(showBackground = true)
    fun FourthOnBoardingScreenPreview() {
        Column(modifier = Modifier.fillMaxSize()) {
            PagerScreen(onBoardingPage = OnBoardingPage.Fourth)
        }
}