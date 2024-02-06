package com.example.diaru.Activities.CreateDiaryActivity

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diaru.ui.theme.darkSkyBlue


@Composable
fun Content(diaryCreateViewModel: DiaryCreateViewModel, context: Context) {
    InputField(diaryCreateViewModel, context)
}

@Composable
fun InputField(diaryCreateViewModel: DiaryCreateViewModel, context: Context) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(bottom = 150.dp, top = 10.dp, start = 10.dp, end = 10.dp)
        , verticalArrangement  = Arrangement.Center
        , horizontalAlignment = Alignment.CenterHorizontally
        ,
    ) {
        Text(
            text = "Pick a fitting title for your diary entry",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 90.dp, start = 8.dp, end = 8.dp),
            style = MaterialTheme.typography.displayLarge.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp,
                lineHeight = 48.sp,
                fontSize = 40.sp
            ),
            color = darkSkyBlue,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        )

        var value by remember { diaryCreateViewModel.title }
        val rainbowColors: List<Color> = listOf(Color.Red, Color(0xFFFFA500), Color.Yellow, Color.Green, Color.Blue, Color(0xFF4B0082), Color(0xFF8A2BE2))
        val brush = remember {
            Brush.linearGradient(
                colors = rainbowColors
            )
        }
        TextField(
            value = value,
            onValueChange = { value = it },
            textStyle = TextStyle(brush = brush, fontSize = 28.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center),
            maxLines = 1,
            modifier = Modifier.fillMaxWidth()

        )

        Button(
            onClick = {
              if (value.isNotEmpty()) {
                  onTitleButtonClick(diaryCreateViewModel, context, value)
              }
              else {
                  Toast.makeText(context, "Please enter a title", Toast.LENGTH_SHORT).show()
              }


            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp, start = 8.dp, end = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = darkSkyBlue,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 4.dp,
                pressedElevation = 8.dp,
                disabledElevation = 0.dp
            )
        ) {
            Text(
                "Continue",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            )
        }
    }
}

fun onTitleButtonClick(diaryCreateViewModel: DiaryCreateViewModel, context: Context, value: String) {
    if (value.isNotEmpty()) {
        diaryCreateViewModel.title.value = value
        diaryCreateViewModel.contentScreen.value = UI_STATES.CONTENT_WRITE
    }
    else {
        Toast.makeText(context, "Please enter a title", Toast.LENGTH_SHORT).show()
    }

}