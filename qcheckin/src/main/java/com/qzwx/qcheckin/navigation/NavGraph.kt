package com.qzwx.qcheckin.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.qzwx.qcheckin.data.CheckInRepository
import com.qzwx.qcheckin.ui.BackUpScreen
import com.qzwx.qcheckin.ui.CalendarScreen
import com.qzwx.qcheckin.ui.CheckInScreen
import com.qzwx.qcheckin.ui.HistoryScreen
import com.qzwx.qcheckin.viewmodel.CheckInViewModel

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun NavGraph(
    checkInRepository : CheckInRepository,
    checkInViewModel : CheckInViewModel // 传递 ViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "check_in") {
        composable("check_in") {
            CheckInScreen(
                navController = navController,
                checkInRepository = checkInRepository
            )
        }
        composable("calendar") {
            CalendarScreen(viewModel = checkInViewModel)
        }
        composable("backupscreen") {
            BackUpScreen(viewModel = checkInViewModel) // 使用传递的 ViewModel
        }
        composable(
            "history/{checkInName}",
            arguments = listOf(
                navArgument("checkInName") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val checkInName = backStackEntry.arguments?.getString("checkInName") ?: ""
            HistoryScreen(
                checkInName = checkInName,
                checkInRepository = checkInRepository,
                viewModel = checkInViewModel // 传递 ViewModel
            )
        }
    }
}