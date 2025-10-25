package com.ramurame.handicraft.presentation.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramurame.handicraft.domain.model.Order
import com.ramurame.handicraft.domain.model.OrderStatus
import com.ramurame.handicraft.domain.usecase.GetAllOrdersUseCase
import com.ramurame.handicraft.domain.usecase.GetOrdersByStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel untuk Orders List
 */
@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val getAllOrdersUseCase: GetAllOrdersUseCase,
    private val getOrdersByStatusUseCase: GetOrdersByStatusUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(OrdersUiState())
    val uiState: StateFlow<OrdersUiState> = _uiState.asStateFlow()

    init {
        loadAllOrders()
    }

    fun loadAllOrders() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, selectedFilter = null)

            try {
                getAllOrdersUseCase().collect { orders ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        orders = orders,
                        filteredOrders = orders
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

    fun filterByStatus(status: OrderStatus?) {
        if (status == null) {
            loadAllOrders()
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                selectedFilter = status
            )

            try {
                getOrdersByStatusUseCase(status).collect { orders ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        filteredOrders = orders
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

    fun searchOrders(query: String) {
        val filtered = if (query.isBlank()) {
            _uiState.value.orders
        } else {
            _uiState.value.orders.filter { order ->
                order.customerName.contains(query, ignoreCase = true) ||
                        order.id.contains(query, ignoreCase = true) ||
                        order.customerPhone.contains(query, ignoreCase = true)
            }
        }

        _uiState.value = _uiState.value.copy(
            filteredOrders = filtered,
            searchQuery = query
        )
    }
}

/**
 * UI State untuk Orders List
 */
data class OrdersUiState(
    val isLoading: Boolean = false,
    val orders: List<Order> = emptyList(),
    val filteredOrders: List<Order> = emptyList(),
    val selectedFilter: OrderStatus? = null,
    val searchQuery: String = "",
    val error: String? = null
)
