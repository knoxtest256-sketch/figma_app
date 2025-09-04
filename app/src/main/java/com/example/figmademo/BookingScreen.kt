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
fun BookingScreen(navController: NavController) {
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
                text = "Book a Table",
                fontSize = 24.sp,
                fontWeight = FontWeight.W600,
                color = TextPrimary
            )
            Text("📍", fontSize = 24.sp)
        }

        // Search bar
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Search restaurants...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            leadingIcon = { Text("🔍", fontSize = 20.sp) }
        )

        // Content
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Categories
            item {
                Text(
                    text = "Categories",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W600,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(8.dp))
                // Category chips would go here
            }

            // Restaurants
            items(getSampleBookings()) { booking ->
                BookingCard(booking = booking)
            }
        }

        // Bottom Navigation
        BookingTabBar(navController)
    }
}

@Composable
fun BookingCard(booking: BookingListing) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            // Restaurant image
            AsyncImage(
                model = booking.imageUrl,
                contentDescription = booking.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentScale = ContentScale.Crop
            )

            // Content
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = booking.name,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W600,
                            color = TextPrimary
                        )
                        Text(
                            text = booking.cuisine,
                            fontSize = 14.sp,
                            color = TextSecondary
                        )
                    }

                    // Rating
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text("⭐", fontSize = 16.sp)
                        Text(
                            text = booking.rating.toString(),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W600,
                            color = TextPrimary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Location and price
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text("📍", fontSize = 14.sp)
                        Text(
                            text = booking.location,
                            fontSize = 14.sp,
                            color = TextSecondary
                        )
                    }

                    Text(
                        text = booking.priceRange,
                        fontSize = 14.sp,
                        color = AccentColor,
                        fontWeight = FontWeight.W600
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Book button
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AccentColor)
                ) {
                    Text(
                        text = "Book Now",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun BookingTabBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(78.dp)
            .background(BackgroundColor)
            .padding(horizontal = 26.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BookingTabItem(icon = "🏠", isSelected = false, onClick = { navController.navigate("social_feed") })
        BookingTabItem(icon = "🔍", isSelected = false, onClick = {})
        BookingTabItem(icon = "📅", isSelected = true, onClick = {})
        BookingTabItem(icon = "🔔", isSelected = false, onClick = {})
        BookingTabItem(icon = "👤", isSelected = false, onClick = {})
    }
}

@Composable
fun BookingTabItem(icon: String, isSelected: Boolean, onClick: () -> Unit) {
    Text(
        text = icon,
        fontSize = 24.sp,
        color = if (isSelected) TextPrimary else TextSecondary,
        modifier = Modifier.padding(8.dp)
    )
}

// Data model
data class BookingListing(
    val name: String,
    val cuisine: String,
    val rating: Double,
    val location: String,
    val priceRange: String,
    val imageUrl: String
)

fun getSampleBookings(): List<BookingListing> {
    return listOf(
        BookingListing(
            name = "The Italian Kitchen",
            cuisine = "Italian",
            rating = 4.5,
            location = "Downtown",
            priceRange = "$$",
            imageUrl = "https://picsum.photos/400/200?random=50"
        ),
        BookingListing(
            name = "Sushi Palace",
            cuisine = "Japanese",
            rating = 4.8,
            location = "Midtown",
            priceRange = "$$$" ,
            imageUrl = "https://picsum.photos/400/200?random=51"
        ),
        BookingListing(
            name = "Burger Joint",
            cuisine = "American",
            rating = 4.2,
            location = "Uptown",
            priceRange = "$",
            imageUrl = "https://picsum.photos/400/200?random=52"
        )
    )
}