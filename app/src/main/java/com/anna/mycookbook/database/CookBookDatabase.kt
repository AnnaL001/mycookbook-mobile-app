package com.anna.mycookbook.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anna.mycookbook.database.dao.CategoryDao
import com.anna.mycookbook.database.dao.IngredientDao
import com.anna.mycookbook.database.dao.RecipeDao
import com.anna.mycookbook.database.dao.StepDao
import com.anna.mycookbook.database.entity.Category
import com.anna.mycookbook.database.entity.Ingredient
import com.anna.mycookbook.database.entity.Recipe
import com.anna.mycookbook.database.entity.Step
import com.anna.mycookbook.database.migrations.MIGRATION_1_2
import com.anna.mycookbook.database.migrations.MIGRATION_2_3

@Database(entities = [Category::class, Recipe::class, Ingredient::class, Step::class],
    version = 3)
abstract class CookBookDatabase: RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun recipeDao(): RecipeDao
    abstract fun ingredientDao(): IngredientDao
    abstract fun stepDao(): StepDao

    companion object{
        private var databaseInstance: CookBookDatabase ?= null

        fun getDatabase(context: Context): CookBookDatabase =
            databaseInstance ?: synchronized(this){
                databaseInstance ?: buildDatabase(context).also { databaseInstance = it }
            }


        private fun buildDatabase(context: Context): CookBookDatabase {
            return Room.databaseBuilder(
                context.applicationContext, CookBookDatabase::class.java,
                "cookbook-database"
            ).addMigrations(MIGRATION_1_2, MIGRATION_2_3).build()
        }
    }
}