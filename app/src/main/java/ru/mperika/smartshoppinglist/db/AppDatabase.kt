package ru.mperika.smartshoppinglist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.mperika.smartshoppinglist.data.Product

@Database(entities = [Product::class], version = 11)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDAO() : ProductDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance

                return instance
            }
        }
    }
}