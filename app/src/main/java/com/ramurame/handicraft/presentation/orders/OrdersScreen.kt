@file:OptIn(ExperimentalMaterial3Api::class)

package com.ramurame.handicraft.presentation.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramurame.handicraft.domain.model.Order
import com.ramurame.handicraft.domain.model.OrderStatus
import com.ramurame.handicraft.presentation.dashboard.getStatusColor
import com.ramurame.handicraft.util.CurrencyFormatter
import com.ramurame.handicraft.util.DateFormatter

/**
 * Orders List Screen - Halaman daftar orderan
 */
@Composable
fun OrdersScreen(
    onNavigateBack: () -> Unit,
    onNavigateToDetail: (String) -> Unit,
    viewModel: OrdersViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showFilterMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            "Daftar Orderan",
                            style = MaterialTheme.typography.headlineMedium
                        )
                        if (uiState.selectedFilter != null) {
                            Text(
                                "Filter: ${uiState.selectedFilter!!.displayName}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Kembali",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { showFilterMenu = true }) {
                        Icon(
                            Icons.Default.FilterList,
                            contentDescription = "Filter",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }

                    // Filter Menu
                    DropdownMenu(
                        expanded = showFilterMenu,
                        onDismissRequest = { showFilterMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Semua Order") },
                            onClick = {
                                viewModel.filterByStatus(null)
                                showFilterMenu = false
                            }
                        )
                        Divider()
                        OrderStatus.values().forEach { status ->
                            DropdownMenuItem(
                                text = { Text("${status.emoji} ${status.displayName}") },
                                onClick = {
                                    viewModel.filterByStatus(status)
                                    showFilterMenu = false
                                }
                            )
                        }
                    }

                    IconButton(onClick = { viewModel.loadAllOrders() }) {
                        Icon(
                            Icons.Default.Refresh,
                            contentDescription = "Refresh",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                OrdersContent(
                    orders = uiState.filteredOrders,
                    onOrderClick = onNavigateToDetail
                )
            }
        }
    }
}

@Composable
fun OrdersContent(
    orders: List<Order>,
    onOrderClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        // Summary Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Total: ${orders.size} order",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            val totalRevenue = orders.sumOf { it.totalPrice }
            Text(
                "Total Nilai: ${CurrencyFormatter.formatToRupiah(totalRevenue)}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (orders.isEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(48.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            Icons.Default.SearchOff,
                            contentDescription = null,
                            modifier = Modifier.size(64.dp),
                            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            "Tidak ada order ditemukan",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(orders) { order ->
                    OrderListItem(
                        order = order,
                        onClick = { onOrderClick(order.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun OrderListItem(
    order: Order,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left Side - Order Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    order.getFormattedOrderNumber(),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        order.customerName,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Phone,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        order.customerPhone,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.CalendarToday,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        DateFormatter.formatDateTime(order.orderDate),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "${order.items.size} item",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Right Side - Price and Status
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    CurrencyFormatter.formatToRupiah(order.totalPrice),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(12.dp))

                Surface(
                    color = getStatusColor(order.currentStatus),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        "${order.currentStatus.emoji} ${order.currentStatus.displayName}",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Medium,
                        color = androidx.compose.ui.graphics.Color.White
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        "Lihat Detail",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}
