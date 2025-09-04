package com.example.figmademo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
fun EcommerceScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        // Status Bar
        StatusBar()

        // Search bar
        SearchBar()

        // Results count
        Text(
            text = "99 results",
            fontSize = 14.sp,
            color = AccentColor,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        // Filter and Sort
        FilterSortBar()

        // Content
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            // Categories row
            item {
                CategoriesRow()
            }

            // Products grid would go here
            items(getSampleProducts()) { product ->
                ProductCard(product = product)
            }

            // Banner
            item {
                PromoBanner()
            }

            // More products
            items(getMoreProducts()) { product ->
                ProductCard(product = product)
            }
        }

        // Tab Bar
        EcommerceTabBar(navController)
    }
}

@Composable
fun SearchBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(40.dp)
            .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("🔍", fontSize = 16.sp, color = TextSecondary)
        Text(
            text = "Search",
            fontSize = 16.sp,
            color = TextSecondary,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun FilterSortBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterChip("Filter", false)
        FilterChip("Sort", false)
    }
}

@Composable
fun FilterChip(label: String, isSelected: Boolean) {
    Row(
        modifier = Modifier
            .background(
                if (isSelected) AccentColor else Color.Transparent,
                RoundedCornerShape(6.dp)
            )
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.W500,
            color = if (isSelected) Color.White else AccentColor
        )
        Text("⌄", fontSize = 12.sp, color = if (isSelected) Color.White else AccentColor)
    }
}

@Composable
fun CategoriesRow() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Title",
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                color = TextPrimary
            )
            Text("〉", fontSize = 16.sp, color = TextPrimary)
        }

        // Categories
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(getCategories()) { category ->
                CategoryItem(category = category)
            }
        }
    }
}

@Composable
fun CategoryItem(category: Category) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(76.dp)
                .background(Color(0xFFF7F7F7), RoundedCornerShape(38.dp))
        ) {
            AsyncImage(
                model = category.imageUrl,
                contentDescription = category.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = category.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.W500,
            color = Color(0xFF161823),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ProductCard(product: Product) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Product image
        AsyncImage(
            model = product.imageUrl,
            contentDescription = product.name,
            modifier = Modifier
                .size(148.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        // Product info
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = product.brand,
                fontSize = 12.sp,
                color = Color(0xFF686868)
            )
            Text(
                text = product.name,
                fontSize = 14.sp,
                color = TextPrimary,
                fontWeight = FontWeight.W400
            )
            Text(
                text = "$$${product.price}",
                fontSize = 16.sp,
                color = TextPrimary,
                fontWeight = FontWeight.W500
            )
        }
    }
}

@Composable
fun PromoBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(136.dp)
            .padding(16.dp)
            .background(Color(0xFF5O4QQQ), RoundedCornerShape(8.dp))
            .padding(20.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Column {
            Text(
                text = "Banner title",
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
                color = Color.White,
                lineHeight = 28.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Pagination dots
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                repeat(5) { index ->
                    Box(
                        modifier = Modifier
                            .size(5.dp)
                            .background(
                                if (index == 0) Color.White else Color.White.copy(alpha = 0.3f),
                                RoundedCornerShape(2.5.dp)
                            )
                    )
                }
            }
        }
    }
}

@Composable
fun EcommerceTabBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(78.dp)
            .background(BackgroundColor)
            .padding(horizontal = 26.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        EcommerceTabItem(icon = "🏠", isSelected = false, onClick = { navController.navigate("social_feed") })
        EcommerceTabItem(icon = "🔍", isSelected = false, onClick = {})
        EcommerceTabItem(icon = "🛒", isSelected = true, onClick = {})
        EcommerceTabItem(icon = "🔔", isSelected = false, onClick = {})
        EcommerceTabItem(icon = "👤", isSelected = false, onClick = {})
    }
}

@Composable
fun EcommerceTabItem(icon: String, isSelected: Boolean, onClick: () -> Unit) {
    Text(
        text = icon,
        fontSize = 24.sp,
        color = if (isSelected) TextPrimary else TextSecondary,
        modifier = Modifier.padding(8.dp)
    )
}

// Data models
data class Category(
    val name: String,
    val imageUrl: String
)

data class Product(
    val name: String,
    val brand: String,
    val price: String,
    val imageUrl: String
)

fun getCategories(): List<Category> {
    return listOf(
        Category("Title", "https://picsum.photos/100/100?random=10"),
        Category("Title", "https://picsum.photos/100/100?random=11"),
        Category("Title", "https://picsum.photos/100/100?random=12"),
        Category("Title", "https://picsum.photos/100/100?random=13")
    )
}

fun getSampleProducts(): List<Product> {
    return listOf(
        Product("Product name", "Brand", "10.99", "https://picsum.photos/150/150?random=20"),
        Product("Product name", "Brand", "10.99", "https://picsum.photos/150/150?random=21"),
        Product("Product name", "Brand", "10.99", "https://picsum.photos/150/150?random=22")
    )
}

fun getMoreProducts(): List<Product> {
    return listOf(
        Product("Product name", "Brand", "10.99", "https://picsum.photos/150/150?random=30"),
        Product("Product name", "Brand", "10.99", "https://picsum.photos/150/150?random=31"),
        Product("Product name", "Brand", "10.99", "https://picsum.photos/150/150?random=32")
    )
}