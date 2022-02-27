package com.anna.mycookbook.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.anna.mycookbook.database.entity.Step

@Dao
interface StepDao {
    @Insert
    suspend fun insertAll(vararg steps: Step)
}