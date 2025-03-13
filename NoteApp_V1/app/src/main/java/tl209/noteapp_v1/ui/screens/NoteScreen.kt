package tl209.noteapp_v1.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import tl209.noteapp_v1.ui.viewmodel.NoteViewModel

@Composable
fun NoteScreen(
    viewModel: NoteViewModel = hiltViewModel()
){
    val notes by viewModel.notes.collectAsState()

    LazyColumn {
        items(notes){ note ->
            Text(text = note.title)
        }
    }
}