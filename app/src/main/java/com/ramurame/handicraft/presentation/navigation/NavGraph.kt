package com.ramurame.handicraft.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ramurame.handicraft.presentation.dashboard.NewDashboardScreen
import com.ramurame.handicraft.presentation.detail.OrderDetailScreen
import com.ramurame.handicraft.presentation.orders.OrderManagementScreen
import com.ramurame.handicraft.presentation.orders.OrdersScreen

/**
 * Navigation Graph untuk aplikasi
 */
@Composable
fun RamurameNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Dashboard.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Dashboard Screen
        composable(route = Screen.Dashboard.route) {
            NewDashboardScreen(
                onNavigateToOrderList = {
                    navController.navigate(Screen.OrderManagement.route)
                }
            )
        }

        // Order Management Screen
        composable(route = Screen.OrderManagement.route) {
            OrderManagementScreen(
                onNavigateToDetail = { orderId ->
                    navController.navigate(Screen.OrderDetail.createRoute(orderId))
                },
                onOpenDrawer = { /* TODO: Handle drawer */ }
            )
        }

        // Orders List Screen (Legacy)
        composable(route = Screen.Orders.route) {
            OrdersScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToDetail = { orderId ->
                    navController.navigate(Screen.OrderDetail.createRoute(orderId))
                }
            )
        }

        // Order Detail Screen
        composable(
            route = Screen.OrderDetail.route,
            arguments = listOf(
                navArgument("orderId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("orderId") ?: return@composable
            OrderDetailScreen(
                orderId = orderId,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
