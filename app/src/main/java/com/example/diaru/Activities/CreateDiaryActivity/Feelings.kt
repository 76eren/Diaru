package com.example.diaru.Activities.CreateDiaryActivity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diaru.R
import com.example.diaru.database.diary.DiaryViewModel


@Composable
fun FeelingBar(createViewModel: DiaryCreateViewModel) {
    var feeling by remember { mutableStateOf(-1) }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = "How did you feel today?",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 90.dp, start =8.dp, end = 8.dp),
            style = MaterialTheme.typography.displayLarge.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp,
                lineHeight = 48.sp
            ),
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        )

        Row(modifier = Modifier
            .fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

            val images = listOf(
                R.drawable.crying_lots,
                R.drawable.crying,
                R.drawable.neutral,
                R.drawable.happy,
                R.drawable.very_happy
            )

            images.forEachIndexed { index, image ->
                ClickableImage(imageRes = image) {
                    createViewModel.feeling.intValue = index
                    createViewModel.contentScreen.value = UI_STATES.CONTENT_INPUT
                }
            }
        }
    }
}

@Composable
fun ClickableImage(imageRes: Int, onClick: () -> Unit) {

    Button(onClick = onClick, contentPadding = PaddingValues(0.dp), modifier = Modifier.padding(8.dp)) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Clickable Image",
            modifier = Modifier.size(56.dp)
        )
    }
}