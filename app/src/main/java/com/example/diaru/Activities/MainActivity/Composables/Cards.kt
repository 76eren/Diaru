package com.example.diaru.Activities.MainActivity.Composables

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.diaru.Activities.DiaryView.DiaryView
import com.example.diaru.R
import com.example.diaru.Settings.SettingsHandler
import com.example.diaru.database.diary.DiaryEntity
import com.example.diaru.database.diary.DiaryViewModel
import com.example.diaru.ui.theme.cardColorLightMode
import java.text.SimpleDateFormat

@Composable
fun ShowDiaryEntries(diaryViewModel: DiaryViewModel) {
    val diaryEntries by diaryViewModel.allEntries.observeAsState(initial = emptyList())

    LazyColumn {
        items(diaryEntries) { entry ->
            DiaryEntryItem(entry)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryEntryItem(entry: DiaryEntity) {
    val context = LocalContext.current
    val dateInt = entry.date
    val formatter = SimpleDateFormat("dd-MM-yyyy : HH:mm")
    val formattedDate: String = formatter.format(dateInt)
    val settings = SettingsHandler()

    val cardColor: Color
    cardColor = if (settings.getSettingBoolean("preference_theme", false, context)) {
        MaterialTheme.colorScheme.background
    } else {
        cardColorLightMode
    }

    Card(
        onClick = {
            val intent = Intent(context, DiaryView::class.java)
            intent.putExtra("id", entry.id)
            context.startActivity(intent)

        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),

        colors = CardDefaults.cardColors(
            containerColor = cardColor
        ),

        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Image(
                painter = painterResource(id = getFeelingImageResource(entry.feeling)),
                contentDescription = "Feeling image",
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = entry.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = formattedDate,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }


}


fun getFeelingImageResource(feelingNumber: Int): Int {
    return when (feelingNumber) {
        0 -> R.drawable.crying_lots
        1 -> R.drawable.crying
        2 -> R.drawable.neutral
        3 -> R.drawable.happy
        4 -> R.drawable.very_happy
        else -> R.drawable.ic_launcher_background
    }
}