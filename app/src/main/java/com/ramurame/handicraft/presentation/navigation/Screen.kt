package com.ramurame.handicraft.presentation.navigation

/**
 * Sealed class untuk mendefinisikan screens dalam aplikasi
 */
sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard")
    object Orders : Screen("orders")
    object OrderManagement : Screen("order_management")
    object OrderDetail : Screen("order_detail/{orderId}") {
        fun createRoute(orderId: String) = "order_detail/$orderId"
    }
}
