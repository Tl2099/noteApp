package tl209.noteapp_v1.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import tl209.noteapp_v1.data.local.Note
import tl209.noteapp_v1.data.repository.NoteRepository
import javax.inject.Inject

@HiltViewModel //Danh dau NoteViewModel la mot ViewModel duoc quan ly boi Hilt
class NoteViewModel @Inject constructor(
    //Hilt tu dong Inject NoteRepository vao ViewModel
    //Khong can tao thu cong NoteRepository
    private val repository: NoteRepository
): ViewModel() {
    val notes: StateFlow<List<Note>> = repository.getAllNotes().stateIn(
        //repository.getAllNotes() tra ve Flow..., giup du lieu tu dong cap nhat khi co thay doi trong Database
        //Dung stateIn() de chuyen Flow thanh StateFlow
        //viewModelScope: Chay trong pham vi cua ViewModel
        //SharingStarted.WhileSubscribed(5000):
        //-> Khi khong con ai quan sat du lieu, VM se giu du lieu trong 5000ms (5s) truoc khi bi huy
        //-> emptyList(): Gia tri mac dinh la mot danh sach trong neu chua co du lieu
        viewModelScope, SharingStarted.Lazily, emptyList()
    )

    fun getNoteById(noteId: Int?): Flow<Note?> {
        return if (noteId == null) MutableStateFlow(null) else repository.getNoteId(noteId)
    }

    fun saveNote(noteId: Int?, title: String, content: String) {
        val newNote = Note(
            id = noteId ?: 0,
            title = title,
            content = content,
            timestamp = System.currentTimeMillis()
        )
        viewModelScope.launch {
            if (noteId == null) {
                repository.insertNote(newNote)
            } else {
                repository.updateNote(newNote)
            }
        }
    }

    fun addNote(note: Note){
        viewModelScope.launch {
            repository.insertNote(note)
        }
    }

    fun updateNote(note: Note){
        viewModelScope.launch {
            repository.updateNote(note)
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }

}