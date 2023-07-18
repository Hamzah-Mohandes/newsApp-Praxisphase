package com.example.newsapp_praxisphase.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp_praxisphase.models.Article


@Database(
    entities = [Article::class],
    version = 3
)
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase(){
abstract fun getArticleDoa(): ArticleDAO


companion object{
    @Volatile
    private var artticleDbInstance : ArticleDatabase? = null
     private val LOCK = Any()

    operator fun invoke(context: Context) = artticleDbInstance ?: synchronized(LOCK)
    {
        artticleDbInstance ?: createDatabaseInstance(context).also {
            artticleDbInstance = it
        }
    }

    private fun createDatabaseInstance(context: Context) =
        Room.databaseBuilder(
            context, ArticleDatabase::class.java,"articles_db.db"
        ).fallbackToDestructiveMigration().build()


}

}