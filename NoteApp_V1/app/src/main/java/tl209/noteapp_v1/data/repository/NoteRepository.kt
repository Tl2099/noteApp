package tl209.noteapp_v1.data.repository

import kotlinx.coroutines.flow.Flow
import tl209.noteapp_v1.data.local.Note
import tl209.noteapp_v1.data.local.NoteDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
//Dam bao chi co mot instance duy nhat cua NoteRepository ton tai trong
//toan bo ung dung
class NoteRepository @Inject constructor(private val noteDao: NoteDao) {
    //@Inject constructor giup Hilt tu dong cung cap NoteDao khi tao NoteRepository
    //Khong can khoi tao thu cong, Hilt se quan ly viec inject dependency
    fun getAllNotes(): Flow<List<Note>> = noteDao.getAllNotes()
    suspend fun insertNote(note: Note) = noteDao.inserNote(note)
    suspend fun updateNote(note: Note) = noteDao.updateNote(note)
    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)
    fun getNoteId(noteId: Int):Flow<Note?> = noteDao.getNoteById(noteId)
}