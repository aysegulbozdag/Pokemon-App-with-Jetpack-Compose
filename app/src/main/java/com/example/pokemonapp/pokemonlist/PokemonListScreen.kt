package com.example.pokemonapp.pokemonlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.request.ImageRequest
import com.example.pokemonapp.R
import com.example.pokemonapp.data.models.PokedexListEntry
import com.google.accompanist.coil.CoilImage

@Composable
fun PokemonListScreen(navController: NavController) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_international_pok_mon_logo_foreground),
                contentDescription = "Pokemon",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
            )

            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                hint = "Search..."
            ) {

            }
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier,
    hint: String,
    onSearch: (String) -> Unit
) {
    var text by remember {
        mutableStateOf("")
    }

    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = it.isFocused
                }
        )

        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }

    @Composable
    fun PokedexEntry(
        entry: PokedexListEntry,
        navController: NavController,
        modifier: Modifier,
        viewModel: PokemonListviewModel = hiltViewModel()
    ) {
        val defaultDominantColor = MaterialTheme.colorScheme.surface
        var dominantColor by remember {
            mutableStateOf(defaultDominantColor)
        }

        Box(modifier = modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(
                Brush.verticalGradient(
                    listOf(
                        dominantColor,
                        defaultDominantColor
                    )
                )
            )
            .clickable {
                navController.navigate("pokemon_detail_screen/${dominantColor.toArgb()}/${entry.pokemonName}")
            }) {
            Column {
                CoilImage(
                    request = ImageRequest.Builder(LocalContext.current)
                        .data(entry.imageUrl)
                        .target {
                            viewModel.calDominantColor(it) { color ->
                                dominantColor = color
                            }
                        }.build(),
                    contentDescription = entry.pokemonName,
                    fadeIn = true,
                    modifier = Modifier
                        .size(120.dp)
                        .align(CenterHorizontally)
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.scale(0.5f)
                    )
                }

                Text(
                    text = entry.pokemonName,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

            }
        }
    }

    @Composable
    fun PokedexRow(
        rowIndex: Int,
        entries: List<PokedexListEntry>,
        navController: NavController
    ) {
        Column {
            Row {
                PokedexEntry(
                    entry = entries[rowIndex * 2],
                    navController = navController,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(16.dp))
                if (entries.size >= rowIndex * 2 + 1)
                    PokedexEntry(
                        entry = entries[rowIndex * 2 + 1],
                        navController = navController,
                        modifier = Modifier.weight(1f)
                    ) else
                    Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}