package com.bombadu.aprikot.local

import android.content.Context
import androidx.room.*import com.bombadu.aprikot.network.CategoryData


@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategories(vararg categoryEntity: CategoryEntity)
}


@Database(
    entities = [CategoryEntity::class],
    version = 2,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    abstract val categoryDao: CategoryDao
}

private lateinit var INSTANCE: LocalDatabase

fun getDatabase(context: Context): LocalDatabase {
    synchronized(LocalDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                LocalDatabase::class.java,
                "database"
            ).build()
        }
    }
    return INSTANCE
}