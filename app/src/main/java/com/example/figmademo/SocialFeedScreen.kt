package com.example.figmademo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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

// Color palette from Figma design
val BackgroundColor = Color(0xFFFFFFFF)
val TextPrimary = Color(0xFF000000)
val TextSecondary = Color(0xFF828282)
val AccentColor = Color(0xFFFE2C55)
val BorderColor = Color(0xFFE6E6E6)

@Composable
fun SocialFeedScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        // Status Bar
        StatusBar()

        // Header with tabs
        FeedHeader()

        // Feed content
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(getSamplePosts()) { post ->
                PostItem(post = post)
            }
        }

        // Tab Bar
        SocialTabBar(navController)
    }
}

@Composable
fun StatusBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .background(BackgroundColor)
            .padding(horizontal = 21.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "9:41",
            fontSize = 15.sp,
            color = TextPrimary
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Signal bars
            Box(
                modifier = Modifier
                    .width(17.dp)
                    .height(10.67.dp)
                    .background(Color.Transparent)
            )
            // WiFi
            Box(
                modifier = Modifier
                    .width(15.27.dp)
                    .height(10.97.dp)
                    .background(Color.Transparent)
            )
            // Battery
            Box(
                modifier = Modifier
                    .width(24.33.dp)
                    .height(11.33.dp)
                    .background(Color.Transparent)
            )
        }
    }
}

@Composable
fun FeedHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BackgroundColor)
    ) {
        // Tab selector
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Following",
                fontSize = 14.sp,
                fontWeight = FontWeight.W600,
                color = TextSecondary
            )
            Text(
                text = "For you",
                fontSize = 14.sp,
                fontWeight = FontWeight.W600,
                color = TextPrimary
            )
            Text(
                text = "Favorites",
                fontSize = 14.sp,
                fontWeight = FontWeight.W600,
                color = TextSecondary
            )
        }

        // Indicator line
        Box(
            modifier = Modifier
                .width(24.dp)
                .height(2.dp)
                .background(Color(0xFF000000))
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun PostItem(post: Post) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        // Post Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Avatar
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFF7F7F7))
                ) {
                    AsyncImage(
                        model = post.avatarUrl,
                        contentDescription = "Avatar",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                // User info
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = post.username,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W600,
                            color = TextPrimary
                        )
                        Text(
                            text = "in ${post.group}",
                            fontSize = 14.sp,
                            color = TextSecondary
                        )
                    }
                    Text(
                        text = post.timestamp,
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }
            }

            // More options
            IconButton(onClick = {}) {
                Text("⋯", fontSize = 20.sp, color = TextPrimary)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Post image
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(299.dp),
            shape = RoundedCornerShape(4.dp)
        ) {
            AsyncImage(
                model = post.imageUrl,
                contentDescription = "Post image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Post actions
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Likes
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(onClick = {}) {
                    Text("♥", fontSize = 20.sp, color = TextPrimary)
                }
                Text(
                    text = "${post.likes} likes",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W500,
                    color = TextPrimary
                )
            }

            // Comments
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(onClick = {}) {
                    Text("💬", fontSize = 20.sp, color = TextPrimary)
                }
                Text(
                    text = "${post.comments} comments",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W500,
                    color = TextPrimary
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Post description
        Text(
            text = post.description,
            fontSize = 14.sp,
            color = TextPrimary,
            lineHeight = 20.sp
        )
    }
}

@Composable
fun SocialTabBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(78.dp)
            .background(BackgroundColor)
            .padding(horizontal = 26.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TabBarItem(icon = "🏠", label = "Home", isSelected = true)
        TabBarItem(icon = "🔍", label = "Search", isSelected = false)
        TabBarItem(icon = "➕", label = "Create", isSelected = false)
        TabBarItem(icon = "🔔", label = "Notifications", isSelected = false)
        TabBarItem(icon = "👤", label = "Profile", isSelected = false)
    }
}

@Composable
fun TabBarItem(icon: String, label: String, isSelected: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = icon,
            fontSize = 24.sp,
            color = if (isSelected) TextPrimary else TextSecondary
        )
    }
}

// Data models
data class Post(
    val username: String,
    val group: String,
    val timestamp: String,
    val avatarUrl: String,
    val imageUrl: String,
    val description: String,
    val likes: Int,
    val comments: Int
)

fun getSamplePosts(): List<Post> {
    return listOf(
        Post(
            username = "Helena",
            group = "Group name",
            timestamp = "3 min ago",
            avatarUrl = "https://picsum.photos/100/100?random=1",
            imageUrl = "https://picsum.photos/400/400?random=1",
            description = "Post description",
            likes = 21,
            comments = 4
        ),
        Post(
            username = "Daniel",
            group = "Group Name",
            timestamp = "2 hrs ago",
            avatarUrl = "https://picsum.photos/100/100?random=2",
            imageUrl = "https://picsum.photos/400/400?random=2",
            description = "Body text for a post. Since it's a social app, sometimes it's a hot take, and sometimes it's a question.",
            likes = 6,
            comments = 18
        ),
        Post(
            username = "Oscar",
            group = "Group Name",
            timestamp = "1 day ago",
            avatarUrl = "https://picsum.photos/100/100?random=3",
            imageUrl = "https://picsum.photos/400/400?random=3",
            description = "Another post",
            likes = 58,
            comments = 5
        )
    )
}