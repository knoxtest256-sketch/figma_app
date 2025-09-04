package com.example.figmademo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun CheckoutScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        // Status Bar
        StatusBar()

        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Checkout",
                fontSize = 24.sp,
                fontWeight = FontWeight.W600,
                color = TextPrimary
            )
            Text("〈", fontSize = 24.sp, color = TextPrimary)
        }

        // Content
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Delivery address
            item {
                CheckoutSection(title = "Delivery Address") {
                    AddressCard()
                }
            }

            // Payment method
            item {
                CheckoutSection(title = "Payment Method") {
                    PaymentMethodCard()
                }
            }

            // Order summary
            item {
                CheckoutSection(title = "Order Summary") {
                    OrderSummaryCard()
                }
            }
        }

        // Bottom section with total and checkout button
        CheckoutBottomSection(navController)
    }
}

@Composable
fun CheckoutSection(title: String, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.W600,
            color = TextPrimary,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        content()
    }
}

@Composable
fun AddressCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("🏠", fontSize = 24.sp)
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Home",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    color = TextPrimary
                )
                Text(
                    text = "123 Main Street\nNew York, NY 10001\nUnited States",
                    fontSize = 14.sp,
                    color = TextSecondary,
                    lineHeight = 20.sp
                )
            }
            Text("〉", fontSize = 20.sp, color = TextSecondary)
        }
    }
}

@Composable
fun PaymentMethodCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFF1A1F71), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("💳", fontSize = 20.sp)
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "•••• •••• •••• 4242",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    color = TextPrimary
                )
                Text(
                    text = "Expires 12/25",
                    fontSize = 14.sp,
                    color = TextSecondary
                )
            }
            Text("〉", fontSize = 20.sp, color = TextSecondary)
        }
    }
}

@Composable
fun OrderSummaryCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Order items
            OrderItem(
                name = "Wireless Headphones",
                price = "$299.99",
                imageUrl = "https://picsum.photos/80/80?random=60"
            )
            OrderItem(
                name = "Phone Case",
                price = "$29.99",
                imageUrl = "https://picsum.photos/80/80?random=61"
            )

            Divider(modifier = Modifier.padding(vertical = 12.dp))

            // Subtotal
            OrderTotalRow(label = "Subtotal", amount = "$329.98")
            OrderTotalRow(label = "Shipping", amount = "$9.99")
            OrderTotalRow(label = "Tax", amount = "$26.40")

            Divider(modifier = Modifier.padding(vertical = 12.dp))

            // Total
            OrderTotalRow(
                label = "Total",
                amount = "$366.37",
                isTotal = true
            )
        }
    }
}

@Composable
fun OrderItem(name: String, price: String, imageUrl: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = name,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = name,
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,
                color = TextPrimary
            )
        }

        Text(
            text = price,
            fontSize = 16.sp,
            fontWeight = FontWeight.W600,
            color = TextPrimary
        )
    }
}

@Composable
fun OrderTotalRow(label: String, amount: String, isTotal: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = if (isTotal) 18.sp else 16.sp,
            fontWeight = if (isTotal) FontWeight.W700 else FontWeight.W400,
            color = if (isTotal) TextPrimary else TextSecondary
        )
        Text(
            text = amount,
            fontSize = if (isTotal) 18.sp else 16.sp,
            fontWeight = if (isTotal) FontWeight.W700 else FontWeight.W400,
            color = if (isTotal) TextPrimary else TextSecondary
        )
    }
}

@Composable
fun CheckoutBottomSection(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Promo code
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Enter promo code") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            trailingIcon = {
                TextButton(onClick = {}) {
                    Text("Apply", color = AccentColor)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Checkout button
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AccentColor)
        ) {
            Text(
                text = "Place Order • $366.37",
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                color = Color.White,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}