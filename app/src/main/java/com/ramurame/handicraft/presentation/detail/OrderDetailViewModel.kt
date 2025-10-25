package com.ramurame.handicraft.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramurame.handicraft.domain.model.Order
import com.ramurame.handicraft.domain.model.OrderStatus
import com.ramurame.handicraft.domain.usecase.GetOrderDetailUseCase
import com.ramurame.handicraft.domain.usecase.UpdateOrderStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel untuk Order Detail
 */
@HiltViewModel
class OrderDetailViewModel @Inject constructor(
    private val getOrderDetailUseCase: GetOrderDetailUseCase,
    private val updateOrderStatusUseCase: UpdateOrderStatusUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val orderId: String = checkNotNull(savedStateHandle["orderId"])

    private val _uiState = MutableStateFlow(OrderDetailUiState())
    val uiState: StateFlow<OrderDetailUiState> = _uiState.asStateFlow()

    init {
        loadOrderDetail()
    }

    fun loadOrderDetail() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                val order = getOrderDetailUseCase(orderId)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    order = order
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun updateStatus(newStatus: OrderStatus) {
        viewModelScope.launch {
            try {
                updateOrderStatusUseCase(orderId, newStatus)
                loadOrderDetail() // Reload to get updated data
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message)
            }
        }
    }
}

/**
 * UI State untuk Order Detail
 */
data class OrderDetailUiState(
    val isLoading: Boolean = false,
    val order: Order? = null,
    val error: String? = null
)
