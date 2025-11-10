package com.woofme.woofme.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.screen.ChatDetailScreen
import com.example.myapplication.screen.HomeScreen
import com.example.myapplication.screen.ProfileScreen
import com.woofme.woofme.view.ChatScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier, navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.Home,
        modifier = modifier
    ){
        composable(ScreenRoutes.ChatList){
            ChatScreen(
                onNavigate = {
                    navController.navigate(ScreenRoutes.ChatDetail)
                }
            )
        }
        composable (ScreenRoutes.Profile){
            ProfileScreen()

        }
        composable (ScreenRoutes.ChatDetail){
            ChatDetailScreen(
                onNavigate = { navController.popBackStack()}
            )


        }
        composable(ScreenRoutes.Home) {
            HomeScreen()
        }


    }

}