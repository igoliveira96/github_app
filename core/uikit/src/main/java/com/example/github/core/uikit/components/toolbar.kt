package com.example.github.core.uikit.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.github.core.uikit.R
import com.example.github.core.uikit.utils.SearchAppBarState
import com.example.github.core.uikit.utils.TrailingIconState

@Composable
fun AppTopAppBar(
    pageTitle: String,
    searchAppBarState: SearchAppBarState,
    searchTextState: String,
    changeState: (SearchAppBarState) -> Unit,
    changeText: (String) -> Unit,
    onSearchClicked: () -> Unit,
) {
    when (searchAppBarState) {
        SearchAppBarState.CLOSED -> {
            DefaultTopAppBar(
                pageTitle = pageTitle,
                onSearchClicked = {
                    changeState(SearchAppBarState.OPENED)
                }
            )
        }
        else -> {
            SearchTopAppBar(
                text = searchTextState,
                onTextChange = { text ->
                    changeText(text)
                },
                onCloseClicked = {
                    changeState(SearchAppBarState.CLOSED)
                    changeText("")
                },
                onSearchClicked = { onSearchClicked() }
            )
        }
    }
}

@Composable
fun DefaultTopAppBar(
    pageTitle: String,
    onSearchClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
    ) {
        TopAppBar(
            modifier = Modifier.padding(top = 24.dp),
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            title = {
                Text(
                    text = pageTitle,
                    color = Color.White
                )
            },
            actions = {
                AppBarActions(
                    onSearchClicked = onSearchClicked
                )
            }
        )
    }
}

@Composable
fun AppBarActions(
    onSearchClicked: () -> Unit
) {
    SearchAction(onSearchClicked = onSearchClicked)
}

@Composable
fun SearchAction(
    onSearchClicked: () -> Unit
) {
    IconButton(
        onClick = {
            onSearchClicked()
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "",
            tint = Color.White
        )
    }
}

@Composable
fun SearchTopAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {

    var trailingIconState by remember {
        mutableStateOf(TrailingIconState.DELETE)
    }

    Box(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
    ) {
//        Image(
//            modifier = Modifier.fillMaxSize(),
//            painter = painterResource(id = R.drawable.ic_top_app_bar_bg),
//            contentDescription = "background_image",
//            contentScale = ContentScale.FillBounds
//        )

        Surface(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxSize(),
            color = Color.Transparent,
            elevation = 0.dp
        ) {
            TextField(
                modifier = Modifier.fillMaxSize(),
                value = text,
                onValueChange = {
                    onTextChange(it)
                },
                placeholder = {
                    Text(
                        modifier = Modifier
                            .alpha(ContentAlpha.medium),
                        text = stringResource(id = R.string.search),
                        color = Color.White
                    )
                },
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp
                ),
                singleLine = true,
                leadingIcon = {
                    IconButton(
                        modifier = Modifier
                            .alpha(ContentAlpha.medium),
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                },
                trailingIcon = {
                    IconButton(onClick = {
                        when (trailingIconState) {
                            TrailingIconState.DELETE -> {
                                onTextChange("")
                                trailingIconState = TrailingIconState.CLOSE
                            }
                            TrailingIconState.CLOSE -> {
                                if (text.isNotEmpty()) {
                                    onTextChange("")
                                } else {
                                    onCloseClicked()
                                    trailingIconState = TrailingIconState.DELETE
                                }
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchClicked(text)
                    }
                ),
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    backgroundColor = Color.Transparent
                )
            )
        }

    }
}