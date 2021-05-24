package com.bombadu.aprikot.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategories(vararg categoryEntity: CategoryEntity)

    @Query("SELECT * FROM category_data_table")
    fun getAllCategories(): LiveData<List<CategoryEntity>>
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