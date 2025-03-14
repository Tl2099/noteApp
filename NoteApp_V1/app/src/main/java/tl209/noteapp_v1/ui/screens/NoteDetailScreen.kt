package tl209.noteapp_v1.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import tl209.noteapp_v1.data.local.Note
import tl209.noteapp_v1.ui.viewmodel.NoteViewModel

@Composable
fun NoteDetailScreen(
    noteId: Int?,
    navController: NavController,
    viewModel: NoteViewModel,
    onBack: () -> Unit
) {
    LaunchedEffect(noteId) {
        viewModel.getNoteById(noteId)
    }
    val note by viewModel.currentNote.collectAsState()

    var title by remember { mutableStateOf(note?.title ?: "") }
    var content by remember { mutableStateOf(note?.content ?: "") }

    LaunchedEffect(note) {
        title = note?.title ?: ""
        content = note?.content ?: ""
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (noteId == 0) "New Note" else "Edit Note") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    if (note != null) {
                        IconButton(onClick = {
                            viewModel.deleteNote(note!!)
                            onBack()
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Xóa")
                        }
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Tiêu đề") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Nội dung") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.saveNote(title, content)
                    onBack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Lưu")
            }
        }
    }
}

//@Composable
//fun NoteDetailScreen(
//    note: Note?,
//    navController: NavController,
//    viewModel: NoteViewModel = hiltViewModel(navController.currentBackStackEntry!!),
//    onBack: () -> Unit
//){
//    var title by remember { mutableStateOf(note?.title ?: "") }
//    var content by remember { mutableStateOf(note?.content ?: "") }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Edit Note") },
//                navigationIcon = {
//                    IconButton(onClick = onBack) {
//                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
//                    }
//                }
//            )
//        },
//        floatingActionButton = {
//            FloatingActionButton(onClick = {
//                val newNote = Note(id = note?.id ?: 0, title = title, content = content, timestamp = System.currentTimeMillis())
//                if (note == null) viewModel.addNote(newNote) else viewModel.updateNote(newNote)
//                onBack()
//            }) {
//                Icon(imageVector = Icons.Default.Send, contentDescription = "Save Note")
//            }
//        }
//    ) { padding ->
//        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
//            OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
//            Spacer(modifier = Modifier.height(8.dp))
//            OutlinedTextField(value = content, onValueChange = { content = it }, label = { Text("Content") }, modifier = Modifier.fillMaxHeight(0.7f))
//        }
//    }
//}