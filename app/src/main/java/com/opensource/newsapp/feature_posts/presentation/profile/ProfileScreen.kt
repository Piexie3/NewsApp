package com.opensource.newsapp.feature_posts.presentation.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.opensource.newsapp.R
import com.opensource.newsapp.feature_posts.domain.model.ImageWithText
import com.opensource.newsapp.navigation.BottomNavItem
import com.opensource.newsapp.navigation.BottomNavMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController
) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Profile",
                        fontWeight = FontWeight.ExtraBold,
                        style = MaterialTheme.typography.titleLarge,
                        color = if (isSystemInDarkTheme())
                            Color.White else
                            Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(
                        modifier = Modifier.clip(CircleShape),
                        onClick = {
                            navController.popBackStack()
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = if (isSystemInDarkTheme())
                                Color.White.copy(.24f) else
                                Color.Black.copy(.24f),
                            contentColor = if (isSystemInDarkTheme())
                                Color.White else
                                Color.Black,
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIos,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(
                        modifier = Modifier.clip(CircleShape),
                        onClick = { /*TODO*/ },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = if (isSystemInDarkTheme())
                                Color.White.copy(.24f) else
                                Color.Black.copy(.24f),
                            contentColor = if (isSystemInDarkTheme())
                                Color.White else
                                Color.Black,
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                )
//                backgroundColor = if (isSystemInDarkTheme())
//                    Color.Black.copy(.24f) else
//                    Color.White.copy(.24f),
//                contentColor = if (isSystemInDarkTheme())
//                    Color.White else
//                    Color.Black,
//
                )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .height(48.dp),
                containerColor = if (isSystemInDarkTheme())
                    Color.Black.copy(.24f) else
                    Color.White.copy(.24f),
            ) {
                BottomNavMenu(selectedItem = BottomNavItem.PROFILE, navController)
            }
        }) { PaddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = PaddingValues.calculateBottomPadding(),
                    bottom = PaddingValues.calculateBottomPadding()
                )
                .padding(top = 8.dp),
        ) {
            ProfileSection()
            Spacer(modifier = Modifier.height(8.dp))
            ProfileDescription(
                url = "https://github.com/piexie3",
                description = "An android developer"
            )
            Spacer(modifier = Modifier.height(8.dp))
            PostTabView(
                imageWithTexts = listOf(
                    ImageWithText(
                        imageVector = Icons.Default.Favorite,
                        text = "Favourites"
                    ),
                    ImageWithText(
                        imageVector = Icons.Default.Chat,
                        text = "Chats"
                    ),
                    ImageWithText(
                        imageVector = Icons.Default.Grid4x4,
                        text = "Posts"
                    ),

                )
            ) {
                selectedTabIndex = it
            }
            when (selectedTabIndex){
//                2-> PostSection(
//                    posts = listOf(
//                        painterResource(id = R.drawable.q),
//                        painterResource(id = R.drawable.qq),
//                        painterResource(id = R.drawable.r),
//                        painterResource(id = R.drawable.ss),
//                        painterResource(id = R.drawable.t),
//                        painterResource(id = R.drawable.tt),
//                        painterResource(id = R.drawable.u),
//                        painterResource(id = R.drawable.x),
//                        painterResource(id = R.drawable.xx),
//                        painterResource(id = R.drawable.y),
//                        painterResource(id = R.drawable.yy),
//                        painterResource(id = R.drawable.z),
//                        painterResource(id = R.drawable.kk),
//                        painterResource(id = R.drawable.l),
//                        painterResource(id = R.drawable.ll),
//                        painterResource(id = R.drawable.m),
//                        painterResource(id = R.drawable.mm),
//                    )
//                )
            }
        }
    }
}

@Composable
fun PostSection(
    posts: List<Painter>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier
            .scale(1.01f)
    ) {
        items(posts.size) {
            Image(
                painter = posts[it],
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(
                        width = 1.dp,
                        color = Color.White
                    )
            )
        }
    }
}

@Composable
fun ProfileSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(140.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(R.drawable.profile)
                    .crossfade(true)
                    .build(),
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {

                    }
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .wrapContentHeight()
                    .clip(CircleShape)
                    .size(40.dp)
                    .clickable {

                    }
                    .background(
                        if (isSystemInDarkTheme())
                            Color.Black else
                            Color.White,
                    )
                    .align(Alignment.BottomEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.PhotoCamera,
                    contentDescription = "Change Profile",
                    tint = if (isSystemInDarkTheme())
                        Color.White else
                        Color.Black,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = "Emmanuel Bett",
                fontWeight = FontWeight.ExtraBold,
                color = if (isSystemInDarkTheme())
                    Color.White else
                    Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "@emmanuel_bett",
                fontWeight = FontWeight.Light,
                color = Color.Blue,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.clip(RoundedCornerShape(6.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSystemInDarkTheme())
                        Color.White.copy(.24f) else
                        Color.Black.copy(.24f),
                    contentColor = if (isSystemInDarkTheme())
                        Color.White else
                        Color.Black,
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Edit Profile",
                    fontWeight = FontWeight.ExtraBold,
                    color = if (isSystemInDarkTheme())
                        Color.White else
                        Color.Black,
                )
            }
        }
    }
}

@Composable
fun ProfileDescription(
    url: String?,
    description: String?,
) {
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = description ?: "",
            style = MaterialTheme.typography.bodySmall,
            color = if (isSystemInDarkTheme())
                Color.White else
                Color.Black,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = url ?: "",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Blue,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun PostTabView(
    modifier: Modifier = Modifier,
    imageWithTexts: List<ImageWithText>,
    onTabSelected: (selectedIndex: Int) -> Unit
) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    val inactiveColor = MaterialTheme.colorScheme.secondary
    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.Transparent,
        contentColor = if (isSystemInDarkTheme())
            Color.White else
            Color.Black,
        modifier = modifier
    ) {
        imageWithTexts.forEachIndexed { index, item ->
           Tab(
                selected = selectedTabIndex == index,
                selectedContentColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                unselectedContentColor = inactiveColor,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(index)
                }
            ) {
                Icon(
                    imageVector = item.imageVector,
                    contentDescription = item.text,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(20.dp)
                )
            }
        }
    }
}
