package tl209.noteapp_v1.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

//@Dao
////Danh dau interface nay la mot Data Access Object trong RoomDatabase
////DAO chua cac phuong thuc de thao tac voi database
//interface NoteDao{
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    //Danh dau day la mot lenh Insert
//    //onConflict...Khi gap xung dot(vi du: trung ID)
//    // -> no se thay the du lieu cu bang du lieu moi
//    suspend fun inserNote(note: Note)
//
//    @Delete
//    //Danh dau phuong thuc deleteNote la mot lenh DELETE de xoa
//    //mot note khoi database
//    suspend fun deleteNote(note: Note)
//
//    @Update
//    suspend fun updateNote(note: Note)
//
//    @Query("SELECT * FROM notes WHERE id = :noteId")
//    fun getNoteById(noteId: Int): Flow<Note?>
//
//    @Query("SELECT * FROM notes ORDER BY timestamp DESC")
//    //Day la cau truy van SQL de lay tat ca du lieu tu bang notes
//    // Room se tu dong anh xa du lieu lay duoc thanh danh sach cac doi tuong Note
//    fun getAllNotes(): Flow<List<Note>>
//    //Vi sao getAllNotes() khong co suspend?
//    //-> Vi ham tra ve Flow va Flow tu dong xu ly cac tac vu bat dong bo
//    // va tu dong chay tren mot Coroutine, ta se chi nhan duoc danh sach tinh thay vi
//    // mot dong du lieu lien tuc cap nhat neu dung suspend
//}

@Dao
interface NoteDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserNote(note: Note)
    @Delete
    suspend fun deleteNote(note: Note)
    @Update
    suspend fun updateNote(note: Note)
    @Query("SELECT * FROM notes ORDER BY timestamp DESC")
    fun getAllNotes(): Flow<List<Note>>
    @Query("SELECT * FROM notes WHERE id = :noteId")
    fun getNoteById(noteId: Int): Flow<Note>


}