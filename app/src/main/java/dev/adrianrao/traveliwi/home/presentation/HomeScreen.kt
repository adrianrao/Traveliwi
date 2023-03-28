package dev.adrianrao.traveliwi.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dev.adrianrao.traveliwi.home.presentation.components.HomeSearchBar

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "A donde viajas?")
        HomeSearchBar(
            onSearch = {},
            onValueChange = {},
            placeholder = "Pais,Ciudad",
            inputText = "",
            modifier = Modifier.fillMaxWidth()
        )
    }
}