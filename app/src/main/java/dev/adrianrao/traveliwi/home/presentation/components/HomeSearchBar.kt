package dev.adrianrao.traveliwi.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.adrianrao.traveliwi.ui.theme.BackgroundGray
import dev.adrianrao.traveliwi.ui.theme.DarkGreen

@Composable
fun HomeSearchBar(
    onSearch: () -> Unit,
    placeholder: String,
    inputText: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = inputText,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = placeholder)
        },
        modifier = modifier.background(color = BackgroundGray, shape = RoundedCornerShape(86.dp)),
        singleLine = true,
        trailingIcon = {
            HomeSearchButton(
                icon = Icons.Default.Search,
                onClick = onSearch,
                modifier = Modifier.padding(8.dp)
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            cursorColor = DarkGreen
        )
    )
}