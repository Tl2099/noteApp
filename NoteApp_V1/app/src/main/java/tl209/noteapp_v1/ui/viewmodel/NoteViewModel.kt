package tl209.noteapp_v1.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
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
) : ViewModel() {
    val notes: StateFlow<List<Note>> = repository.getAllNotes().stateIn(
        //repository.getAllNotes() tra ve Flow..., giup du lieu tu dong cap nhat khi co thay doi trong Database
        //Dung stateIn() de chuyen Flow thanh StateFlow
        //viewModelScope: Chay trong pham vi cua ViewModel
        //SharingStarted.WhileSubscribed(5000):
        //-> Khi khong con ai quan sat du lieu, VM se giu du lieu trong 5000ms (5s) truoc khi bi huy
        //-> emptyList(): Gia tri mac dinh la mot danh sach trong neu chua co du lieu
        viewModelScope, SharingStarted.Lazily, emptyList()
    )

    private val _uiState = MutableStateFlow<NoteUIState>(NoteUIState.Loading)
    val uiState: StateFlow<NoteUIState> = _uiState.asStateFlow()

    private val _currentNote = MutableStateFlow<Note?>(null)
    val currentNote: StateFlow<Note?> = _currentNote

    init {
        getAllNotes()
    }

    private fun getAllNotes(){
        repository.getAllNotes()
            .onStart { _uiState.value = NoteUIState.Loading }
            .catch { execption -> _uiState.value = NoteUIState.Error(execption.message ?: "Unknown error") }
            .onEach { notes -> _uiState.value = NoteUIState.Success(notes) }
            .launchIn(viewModelScope)
    }

    fun getNoteById(noteId: Int?) {
        if (noteId == null) {
            _currentNote.value = null
            return
        }
        viewModelScope.launch {
            repository.getNoteId(noteId).collect { note ->
                _currentNote.value = note
            }
        }
    }

    fun saveNote(title: String, content: String) {
        viewModelScope.launch {
            val note = _currentNote.value?.copy(
                title = title,
                content = content,
                timestamp = System.currentTimeMillis()
            ) ?: Note(
                title = title,
                content = content,
                timestamp = System.currentTimeMillis()
            )
            if (note.id == 0) {
                repository.insertNote(note)
            } else {
                repository.updateNote(note)
            }
            _currentNote.value = null

        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }
    sealed class NoteUIState{
        object Loading: NoteUIState()
        data class Success(val notes: List<Note>): NoteUIState()
        data class Error(val message: String): NoteUIState()
    }
}