package com.example.moviesapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesapp.BottomNavigationBar
import com.example.moviesapp.Domain.FilmItemModel
import com.example.moviesapp.FilmItem
import com.example.moviesapp.R
import com.example.moviesapp.SearchBar
import com.example.moviesapp.ViewModel.MainViewModel
import com.example.moviesapp.ui.theme.MoviesAppTheme
import com.google.android.material.floatingactionbutton.FloatingActionButton
import okhttp3.internal.wait

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen(onItemClick = { item->
                val intent = Intent(this, DetailMovieActivity::class.java)
                intent.putExtra("object", item)
                startActivity(intent)
            })
        }
    }
}

@Composable
@Preview
fun MainScreenPreview(){
    MainScreen()
}

@Composable
fun MainScreen(onItemClick: (FilmItemModel) -> Unit = {}){
    //Scaffold is a layout component that provides a basic structure for common UI elements like top bars, bottom bars, floating action buttons (FABs), drawers, and snackbars.
    Scaffold(
        bottomBar = { BottomNavigationBar() },
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        brush = Brush.linearGradient(
                            listOf(colorResource(R.color.pink), colorResource(R.color.green))
                        ),
                        shape = CircleShape
                    )
                    .padding(3.dp)
            ){
                FloatingActionButton(
                    onClick = {},
                    backgroundColor = colorResource(R.color.black3),
                    modifier = Modifier.size(58.dp),
                    contentColor = Color.White,
                    content = {
                        Icon(
                            painter = painterResource(R.drawable.float_icon),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                )
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        backgroundColor = colorResource(R.color.blackBackground)
    ) {paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .background(color = colorResource(R.color.blackBackground))
        ){
            Image(
                painter = painterResource(R.drawable.bg1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
            MainContent(onItemClick)
        }
    }
}

@Composable
fun MainContent(onItemClick: (FilmItemModel) -> Unit) {

    val viewModel = MainViewModel()
    val upcoming = remember { mutableStateListOf<FilmItemModel>() }
    val newMovies = remember { mutableStateListOf<FilmItemModel>() }

    var showUpcomingLoad by remember { mutableStateOf(true) }
    var showNewMoviesLoad by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.loadUpcoming().observeForever{
            upcoming.clear()
            upcoming.addAll(it)
            showUpcomingLoad = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadItems().observeForever{
            newMovies.clear()
            newMovies.addAll(it)
            showNewMoviesLoad = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 60.dp, bottom = 100.dp)
    ) {
        Text(
            text = "What would you like to watch?",
            style = TextStyle(color = Color.White, fontSize = 25.sp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        )
        SearchBar("Search Movies...")
        SectionTitle("New Movies")

        if(showNewMoviesLoad){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }else{
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(newMovies){item->
                    FilmItem(item, onItemClick)
                }
            }
        }
        SectionTitle("upcoming Movies")
        if(showUpcomingLoad){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }else{
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(upcoming){item->
                    FilmItem(item, onItemClick)
                }
            }
        }
    }
}

@Composable
fun SectionTitle(title: String){
    Text(
        text = title,
        style = TextStyle(color = Color(0xffffc107), fontSize = 18.sp),
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 16.dp, top = 32.dp, bottom = 8.dp)
    )
}

