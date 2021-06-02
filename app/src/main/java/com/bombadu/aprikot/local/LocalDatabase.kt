package com.bombadu.aprikot.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCategories(vararg categoryEntity: CategoryEntity)

    @Query("SELECT * FROM category_data_table")
    fun getAllCategories(): LiveData<List<CategoryEntity>>

    @Query("SELECT EXISTS(SELECT * FROM category_data_table)")
    fun isExists(): Boolean
}


@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertRecipes(vararg recipeEntity: RecipeEntity)


    @Query("SELECT * FROM recipe_list_data_table WHERE category = :category")
    fun getRecipesByCategory(category: String): LiveData<List<RecipeEntity>>

    @Query("SELECT EXISTS(SELECT * FROM recipe_list_data_table WHERE category = :category)")
    fun isRowIsExist(category: String) : Boolean
}


@Dao
interface PreparationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPreparation(vararg preparationEntity: PreparationEntity)

    @Query("SELECT * FROM preparation_data_table WHERE recipeId = :recipeId")
    fun getPreparationById(recipeId: String): LiveData<PreparationEntity>

    @Query("SELECT EXISTS(SELECT * FROM preparation_data_table WHERE recipeId = :id)")
    fun isRowIsExist(id: String) : Boolean

    @Query("SELECT * FROM preparation_data_table WHERE isFavorite = :isFavorite")
    fun getFavorites(isFavorite: Boolean = true): LiveData<List<PreparationEntity>>

}




@Database(
    entities = [CategoryEntity::class, RecipeEntity::class, PreparationEntity::class],
    version = 3,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    abstract val categoryDao: CategoryDao
    abstract val recipeDao: RecipeDao
    abstract val preparationDao: PreparationDao
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