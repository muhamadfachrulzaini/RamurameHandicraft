package com.ramurame.handicraft.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramurame.handicraft.domain.model.Order
import com.ramurame.handicraft.domain.usecase.GetDashboardDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel untuk Dashboard
 */
@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getDashboardDataUseCase: GetDashboardDataUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        loadDashboardData()
    }

    fun loadDashboardData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                // Get today's orders count
                val todayOrdersCount = getDashboardDataUseCase.getTodayOrdersCount()

                // Get today's revenue
                val todayRevenue = getDashboardDataUseCase.getTodayRevenue()

                // Collect today's orders for display
                getDashboardDataUseCase.getTodayOrders().collect { orders ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        todayOrdersCount = todayOrdersCount,
                        todayRevenue = todayRevenue,
                        recentOrders = orders.take(5)
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}

/**
 * UI State untuk Dashboard
 */
data class DashboardUiState(
    val isLoading: Boolean = false,
    val todayOrdersCount: Int = 0,
    val todayRevenue: Long = 0,
    val recentOrders: List<Order> = emptyList(),
    val error: String? = null
)
