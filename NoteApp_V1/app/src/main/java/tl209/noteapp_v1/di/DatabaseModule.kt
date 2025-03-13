package tl209.noteapp_v1.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tl209.noteapp_v1.data.local.NoteDao
import tl209.noteapp_v1.data.local.NoteDatabase
import javax.inject.Singleton

@Module //Danh dau day la mot Hilt Module, noi khai bao cac dependencies
@InstallIn(SingletonComponent::class)
//Xac dinh pham vi cua module nay
//SingletonComponent co nghia la cac dependencies trong module nay se ton tai
//trong suot vong doi cua ung dung
object DatabaseModule{

    @Provides //Danh dau day la mot dependency ma Hilt co the cung cap
    @Singleton//Dam bao NoteDatabase chi co mot instance duy nhat trong toan bo ung dung
    //@ApplicationContext: Inject Context tu Hilt, dam bao dung App Context de tao database
    fun provideDatabase(@ApplicationContext context: Context): NoteDatabase{
        //Tao instacne cua Room Database
        return Room.databaseBuilder(context, NoteDatabase::class.java, "note_db").build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(db: NoteDatabase): NoteDao = db.noteDao()
    //Lay NoteDao tu NoteDatabase va cung cap no cho Hilt
    //Khong can @Singleton vi DAO da duoc quan ly  ben trong database
}