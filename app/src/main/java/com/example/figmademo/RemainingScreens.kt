package com.example.figmademo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
fun DashboardScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        // Status Bar
        StatusBar()

        // Header
        Text(
            text = "Dashboard",
            fontSize = 24.sp,
            fontWeight = FontWeight.W600,
            color = TextPrimary,
            modifier = Modifier.padding(16.dp)
        )

        // Stats Cards
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(getDashboardStats()) { stat ->
                StatCard(stat = stat)
            }
        }

        // Bottom Navigation
        DashboardTabBar(navController)
    }
}

@Composable
fun StatCard(stat: DashboardStat) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(stat.color, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(stat.icon, fontSize = 24.sp)
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stat.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    color = TextPrimary
                )
                Text(
                    text = stat.value,
                    fontSize = 14.sp,
                    color = TextSecondary
                )
            }
        }
    }
}

@Composable
fun DashboardTabBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(78.dp)
            .background(BackgroundColor)
            .padding(horizontal = 26.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TabItem(icon = "🏠", isSelected = false, onClick = { navController.navigate("social_feed") })
        TabItem(icon = "📊", isSelected = true, onClick = {})
        TabItem(icon = "💬", isSelected = false, onClick = { navController.navigate("chat") })
        TabItem(icon = "👤", isSelected = false, onClick = { navController.navigate("sign_in") })
    }
}

@Composable
fun ChatScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        // Status Bar
        StatusBar()

        // Header
        Text(
            text = "Messages",
            fontSize = 24.sp,
            fontWeight = FontWeight.W600,
            color = TextPrimary,
            modifier = Modifier.padding(16.dp)
        )

        // Chat list
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(getChatMessages()) { message ->
                ChatMessageItem(message = message)
            }
        }

        // Bottom Navigation
        ChatTabBar(navController)
    }
}

@Composable
fun ChatMessageItem(message: ChatMessage) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Avatar
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFFF7F7F7))
        ) {
            AsyncImage(
                model = message.avatarUrl,
                contentDescription = "Avatar",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        // Message content
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = message.sender,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    color = TextPrimary
                )
                Text(
                    text = message.timestamp,
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = message.preview,
                fontSize = 14.sp,
                color = TextSecondary,
                maxLines = 2
            )
        }

        // Unread indicator
        if (message.unreadCount > 0) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .background(AccentColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = message.unreadCount.toString(),
                    fontSize = 12.sp,
                    color = Color.White,
                    fontWeight = FontWeight.W600
                )
            }
        }
    }
}

@Composable
fun ChatTabBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(78.dp)
            .background(BackgroundColor)
            .padding(horizontal = 26.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TabItem(icon = "🏠", isSelected = false, onClick = { navController.navigate("social_feed") })
        TabItem(icon = "📊", isSelected = false, onClick = { navController.navigate("dashboard") })
        TabItem(icon = "💬", isSelected = true, onClick = {})
        TabItem(icon = "👤", isSelected = false, onClick = { navController.navigate("sign_in") })
    }
}

@Composable
fun SignInScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Status Bar
        StatusBar()

        Spacer(modifier = Modifier.weight(1f))

        // Logo/Title
        Text(
            text = "Welcome",
            fontSize = 32.sp,
            fontWeight = FontWeight.W700,
            color = TextPrimary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Sign in to continue",
            fontSize = 16.sp,
            color = TextSecondary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Email field
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password field
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Sign in button
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AccentColor)
        ) {
            Text(
                text = "Sign In",
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Social sign in
        Text(
            text = "Or continue with",
            fontSize = 14.sp,
            color = TextSecondary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SocialButton(icon = "🍎", label = "Apple")
            SocialButton(icon = "🇬", label = "Google")
            SocialButton(icon = "🇫", label = "Facebook")
        }

        Spacer(modifier = Modifier.weight(1f))

        // Sign up link
        Text(
            text = "Don't have an account? Sign up",
            fontSize = 14.sp,
            color = AccentColor,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun SocialButton(icon: String, label: String) {
    OutlinedButton(
        onClick = {},
        modifier = Modifier.size(50.dp),
        shape = RoundedCornerShape(12.dp),
        border = null,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color(0xFFF7F7F7)
        )
    ) {
        Text(icon, fontSize = 20.sp)
    }
}

@Composable
fun TabItem(icon: String, isSelected: Boolean, onClick: () -> Unit) {
    Text(
        text = icon,
        fontSize = 24.sp,
        color = if (isSelected) TextPrimary else TextSecondary,
        modifier = Modifier.padding(8.dp)
    )
}

// Data models
data class DashboardStat(
    val title: String,
    val value: String,
    val icon: String,
    val color: Color
)

data class ChatMessage(
    val sender: String,
    val preview: String,
    val timestamp: String,
    val avatarUrl: String,
    val unreadCount: Int
)

fun getDashboardStats(): List<DashboardStat> {
    return listOf(
        DashboardStat("Revenue", "$12,345", "💰", Color(0xFF4CAF50)),
        DashboardStat("Users", "1,234", "👥", Color(0xFF2196F3)),
        DashboardStat("Orders", "567", "📦", Color(0xFFFF9800)),
        DashboardStat("Growth", "+23%", "📈", Color(0xFFE91E63))
    )
}

fun getChatMessages(): List<ChatMessage> {
    return listOf(
        ChatMessage("John Doe", "Hey, how are you doing?", "2m ago", "https://picsum.photos/100/100?random=40", 2),
        ChatMessage("Jane Smith", "Thanks for the help!", "1h ago", "https://picsum.photos/100/100?random=41", 0),
        ChatMessage("Mike Johnson", "See you tomorrow", "3h ago", "https://picsum.photos/100/100?random=42", 1),
        ChatMessage("Sarah Wilson", "Great work on the project", "1d ago", "https://picsum.photos/100/100?random=43", 0)
    )
}