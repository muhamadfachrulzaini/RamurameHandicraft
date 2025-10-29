
package com.ramurame.handicraft.presentation.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Inbox
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramurame.handicraft.presentation.theme.RamurameHandicraftTheme

// --- Data Models ---
enum class OrderStatus(val displayName: String) {
    SEMUA("Semua"),
    PENDING("Pending"),
    PROSES("Proses"),
    SIAP_KIRIM("Siap Kirim"),
    SELESAI("Selesai"),
    DIBATALKAN("Dibatalkan")
}

data class Order(
    val orderId: String,
    val customerName: String,
    val amount: String,
    val date: String,
    val status: OrderStatus
)

// --- Colors ---
val PrimaryOrange = Color(0xFFFF9800)
val SecondaryBrown = Color(0xFF8B6F47)
val StatusBlue = Color(0xFF2196F3)
val StatusGreen = Color(0xFF4CAF50)
val StatusLightBlue = Color(0xFF03A9F4)
val StatusRed = Color(0xFFF44336)
val LightGrayBackground = Color(0xFFF7F7F7)

fun getStatusColor(status: OrderStatus): Color {
    return when (status) {
        OrderStatus.PROSES -> StatusBlue
        OrderStatus.PENDING -> PrimaryOrange
        OrderStatus.SELESAI -> StatusGreen
        OrderStatus.SIAP_KIRIM -> StatusLightBlue
        OrderStatus.DIBATALKAN -> StatusRed
        else -> Color.Gray
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderManagementScreen(
    onNavigateToDetail: (String) -> Unit,
    onOpenDrawer: () -> Unit,
) {
    var selectedStatus by remember { mutableStateOf(OrderStatus.SEMUA) }
    var showSortMenu by remember { mutableStateOf(false) }
    var currentSort by remember { mutableStateOf("Tanggal Terbaru") }
    val isRefreshing by remember { mutableStateOf(false) } // For SwipeRefresh

    // Sample Data
    val allOrders = remember { getSampleOrders() }
    val filteredOrders = remember(selectedStatus, allOrders) {
        if (selectedStatus == OrderStatus.SEMUA) {
            allOrders
        } else {
            allOrders.filter { it.status == selectedStatus }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Daftar Pesanan", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onOpenDrawer) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO: Search */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = { /* TODO: More options */ }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More Options")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        containerColor = LightGrayBackground
    ) { paddingValues ->
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = { /* TODO: Handle refresh logic */ },
            modifier = Modifier.padding(paddingValues)
        ) {
            Column {
                FilterTabBar(
                    selectedStatus = selectedStatus,
                    onStatusSelected = { selectedStatus = it }
                )
                SortBar(
                    currentSort = currentSort,
                    showSortMenu = showSortMenu,
                    onSortClick = { showSortMenu = !showSortMenu },
                    onDismiss = { showSortMenu = false },
                    onSortSelected = {
                        currentSort = it
                        showSortMenu = false
                        // TODO: Implement sorting logic
                    }
                )

                if (filteredOrders.isEmpty()) {
                    EmptyOrderState()
                } else {
                    LazyColumn(
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(filteredOrders, key = { it.orderId }) { order ->
                            OrderCard(order = order, onNavigateToDetail = onNavigateToDetail)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FilterTabBar(
    selectedStatus: OrderStatus,
    onStatusSelected: (OrderStatus) -> Unit
) {
    val filters = listOf(OrderStatus.SEMUA, OrderStatus.PENDING, OrderStatus.PROSES, OrderStatus.SIAP_KIRIM)
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(filters) { status ->
            val isSelected = selectedStatus == status
            Button(
                onClick = { onStatusSelected(status) },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) PrimaryOrange else Color.LightGray.copy(alpha = 0.5f),
                    contentColor = if (isSelected) Color.White else Color.DarkGray
                ),
                elevation = ButtonDefaults.buttonElevation(0.dp, 0.dp)
            ) {
                Text(text = status.displayName, fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun SortBar(
    currentSort: String,
    showSortMenu: Boolean,
    onSortClick: () -> Unit,
    onDismiss: () -> Unit,
    onSortSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onSortClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Sort, contentDescription = "Sort", tint = Color.Gray)
        Spacer(Modifier.width(8.dp))
        Text("Urutkan: $currentSort", color = Color.Gray, fontSize = 14.sp)
        DropdownMenu(
            expanded = showSortMenu,
            onDismissRequest = onDismiss
        ) {
            DropdownMenuItem(text = { Text("Tanggal Terbaru") }, onClick = { onSortSelected("Tanggal Terbaru") })
            DropdownMenuItem(text = { Text("Tanggal Terlama") }, onClick = { onSortSelected("Tanggal Terlama") })
            DropdownMenuItem(text = { Text("Harga Tertinggi") }, onClick = { onSortSelected("Harga Tertinggi") })
            DropdownMenuItem(text = { Text("Harga Terendah") }, onClick = { onSortSelected("Harga Terendah") })
        }
    }
}

@Composable
fun OrderCard(order: Order, onNavigateToDetail: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNavigateToDetail(order.orderId) },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.weight(1f)) {
                    Text(
                        text = "#${order.orderId}",
                        color = SecondaryBrown,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = order.customerName,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = order.date,
                        color = SecondaryBrown,
                        fontSize = 12.sp
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = order.amount,
                        color = PrimaryOrange,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Icon(
                    Icons.Default.ChevronRight,
                    contentDescription = "Navigate to detail",
                    tint = Color.Gray
                )
            }
            StatusBadge(
                status = order.status,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
            )
        }
    }
}

@Composable
fun StatusBadge(status: OrderStatus, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(getStatusColor(status).copy(alpha = 0.15f))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = status.displayName,
            color = getStatusColor(status),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun EmptyOrderState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.Inbox,
            contentDescription = "No Orders",
            modifier = Modifier.size(80.dp),
            tint = Color.LightGray
        )
        Spacer(Modifier.height(16.dp))
        Text(
            "Belum Ada Pesanan",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
        Spacer(Modifier.height(8.dp))
        Text(
            "Saat pesanan baru masuk atau filter diubah, pesanan yang cocok akan muncul di sini. Tarik layar ke bawah untuk memuat ulang.",
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontSize = 14.sp
        )
    }
}

// --- Preview and Sample Data ---

fun getSampleOrders(): List<Order> {
    return listOf(
        Order("#ORD-12345", "Amanda Larasati", "Rp 550.000", "12 Okt 2023", OrderStatus.PROSES),
        Order("#ORD-12346", "Budi Santoso", "Rp 275.000", "12 Okt 2023", OrderStatus.PENDING),
        Order("#ORD-12347", "Citra Dewi", "Rp 1.200.000", "11 Okt 2023", OrderStatus.SELESAI),
        Order("#ORD-12348", "Doni Firmansyah", "Rp 850.000", "11 Okt 2023", OrderStatus.SIAP_KIRIM),
        Order("#ORD-12349", "Eka Wijaya", "Rp 450.000", "10 Okt 2023", OrderStatus.DIBATALKAN),
        Order("#ORD-12350", "Fitriana", "Rp 600.000", "10 Okt 2023", OrderStatus.PROSES)
    )
}

@Preview(showBackground = true)
@Composable
fun OrderManagementScreenPreview() {
    RamurameHandicraftTheme {
        OrderManagementScreen(onNavigateToDetail = {}, onOpenDrawer = {})
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyOrderManagementScreenPreview() {
    RamurameHandicraftTheme {
        // Trick to show empty state by filtering for a status that doesn't exist
        var selectedStatus by remember { mutableStateOf(OrderStatus.SELESAI) }
        val filteredOrders = remember {
             getSampleOrders().filter { it.status == OrderStatus.PROSES && it.orderId == "none" }
        }

        Scaffold(containerColor = LightGrayBackground) { padding ->
             Column(Modifier.padding(padding)) {
                FilterTabBar(selectedStatus = selectedStatus, onStatusSelected = {selectedStatus = it})
                SortBar(currentSort = "Tanggal Terbaru", showSortMenu = false, onSortClick = {}, onDismiss = {}, onSortSelected = {})
                if (filteredOrders.isEmpty()) {
                    EmptyOrderState()
                }
            }
        }
    }
}
