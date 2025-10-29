package com.ramurame.handicraft.presentation.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramurame.handicraft.presentation.theme.RamurameHandicraftTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewDashboardScreen(
    onNavigateToOrderList: () -> Unit = {},
    onNavigateToCreateOrder: () -> Unit = {},
    onNavigateToOrderStatus: () -> Unit = {},
    onNavigateToReports: () -> Unit = {}
) {
    Scaffold(
        topBar = { DashboardTopAppBar() },
        containerColor = Color(0xFFF5F5F5) // Light gray background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                SupportMessage()
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Key Metrics Section
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    MetricCard(
                        title = "Total Orderan Hari Ini",
                        value = "12",
                        change = "+2%",
                        changeColor = Color(0xFF4CAF50),
                        modifier = Modifier.weight(1f)
                    )
                    MetricCard(
                        title = "Perlu Dikerjakan",
                        value = "5",
                        change = "-5%",
                        changeColor = Color(0xFFF44336),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    MetricCard(
                        title = "Siap Dikirim",
                        value = "3",
                        change = "+1%",
                        changeColor = Color(0xFF4CAF50),
                        modifier = Modifier.weight(1f)
                    )
                    MetricCard(
                        title = "Pendapatan Hari Ini",
                        value = "Rp 1.5Jt",
                        change = "+10%",
                        changeColor = Color.White.copy(alpha = 0.8f),
                        backgroundColor = Color(0xFFFF9800),
                        contentColor = Color.White,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Main Menu Section
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text("Menu Utama", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
                Spacer(modifier = Modifier.height(16.dp))
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    MenuItem(icon = Icons.Default.ListAlt, title = "Daftar Semua Orderan", description = "Lihat dan kelola semua orderan yang masuk.", onClick = onNavigateToOrderList)
                    MenuItem(icon = Icons.Default.AddCircleOutline, title = "Buat Orderan Baru", description = "Catat pesanan baru secara manual.", onClick = onNavigateToCreateOrder)
                    MenuItem(icon = Icons.Default.HourglassEmpty, title = "Status Pengerjaan", description = "Lacak status pengerjaan setiap orderan.", onClick = onNavigateToOrderStatus)
                    MenuItem(icon = Icons.Default.Analytics, title = "Laporan & Analitik", description = "Analisis penjualan dan performa toko.", onClick = onNavigateToReports)
                }
            }

            // Recent Activities Section
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text("Aktivitas Terbaru", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
                Spacer(modifier = Modifier.height(16.dp))
            }
            items(getRecentActivities()) { activity ->
                ActivityItem(activity = activity)
            }
            item {
                Spacer(modifier = Modifier.height(16.dp)) // Add space at the bottom
            }
        }
    }
}

@Composable
fun DashboardTopAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Store, contentDescription = "Store Icon", tint = Color(0xFFFF9800), modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text("Selamat Pagi, Ramurame!", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
                Text("Sabtu, 25 Oktober 2025", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            }
        }
        Icon(Icons.Default.AccountCircle, contentDescription = "Profile", modifier = Modifier.size(40.dp), tint = Color.Gray)
    }
}

@Composable
fun SupportMessage() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.SupportAgent, contentDescription = "Support", tint = Color(0xFFFF9800))
            Spacer(modifier = Modifier.width(12.dp))
            Text("Butuh bantuan? Cek panduan atau hubungi support.", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun MetricCard(
    title: String,
    value: String,
    change: String,
    changeColor: Color,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    contentColor: Color = Color.Black
) {
    Card(
        modifier = modifier.height(120.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(title, style = MaterialTheme.typography.bodyMedium, color = contentColor.copy(alpha = 0.8f))
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    value,
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                    color = contentColor
                )
                Text(
                    change,
                    style = MaterialTheme.typography.bodySmall,
                    color = changeColor,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}

@Composable
fun MenuItem(icon: ImageVector, title: String, description: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = title, tint = Color(0xFFFF9800), modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
                Text(description, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            }
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.Gray)
        }
    }
}

data class Activity(val icon: ImageVector, val color: Color, val description: String, val time: String)

fun getRecentActivities(): List<Activity> {
    return listOf(
        Activity(Icons.Default.ShoppingCart, Color(0xFF4CAF50), "Orderan baru #1025 telah dibuat.", "5 menit yang lalu"),
        Activity(Icons.Default.LocalShipping, Color(0xFF2196F3), "Orderan #1024 siap untuk dikirim.", "15 menit yang lalu"),
        Activity(Icons.Default.CheckCircle, Color(0xFFFFC107), "Status orderan #1022 diubah menjadi 'Selesai'.", "1 jam yang lalu"),
        Activity(Icons.Default.Payment, Color(0xFF9C27B0), "Pembayaran untuk orderan #1023 telah dikonfirmasi.", "2 jam yang lalu"),
        Activity(Icons.Default.Cancel, Color(0xFFF44336), "Orderan #1021 telah dibatalkan oleh pelanggan.", "Kemarin")
    )
}

@Composable
fun ActivityItem(activity: Activity) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(activity.color.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(activity.icon, contentDescription = null, tint = activity.color, modifier = Modifier.size(24.dp))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(activity.description, style = MaterialTheme.typography.bodyMedium)
            Text(activity.time, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewDashboardScreenPreview() {
    RamurameHandicraftTheme {
        NewDashboardScreen()
    }
}
