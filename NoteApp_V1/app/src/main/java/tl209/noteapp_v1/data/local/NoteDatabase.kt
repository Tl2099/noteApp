package tl209.noteapp_v1.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
//Dinh nghia danh sach cac bang trong CSDL. O day, Note;:class la mot entity
//tuc la mot bang trong database
//version = 1:Phien ban cua database. Khi ban thay doi scheme, can tang ver nay len
abstract class NoteDatabase: RoomDatabase() {
    //RoomDatabase la mot abstact cung cap cac phuong thuc de thao tac voi CSDL
    //Room su dung Singleton Pattern de dam bao chi co mot instance cua database trong toan bo ung dung
    abstract fun noteDao(): NoteDao
    //Day la mot ham truu tuong, giup Room tu dong tao ra mot doi tuong DAO
    //de thao tac voi bang notes
}