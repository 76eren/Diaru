package com.example.diaru.Activities.CreateDiaryActivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import com.example.diaru.R
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diaru.Activities.CreateDiaryActivity.ui.theme.DiaruTheme
import com.example.diaru.database.diary.DiaryViewModel

class CreateDiaryActivity : ComponentActivity() {
    private val diaryViewModel: DiaryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            DiaruTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    FeelingBar()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FeelingBar() {
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

            images.forEach { image ->
                ClickableImage(imageRes = image,  onClick = { /* TODO: implement this */ })
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


