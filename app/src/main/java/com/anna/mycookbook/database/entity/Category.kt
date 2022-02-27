package com.anna.mycookbook.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @ColumnInfo(name = "category_title") val categoryTitle: String,
    @ColumnInfo(name = "category_image") val categoryImage: String
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}