package com.urbandictionary.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.urbandictionary.data.model.Urban

@Dao
interface UrbanDao {

    @Query("SELECT * FROM Urban")
    fun all(): LiveData<List<Urban>>

    @Query("SELECT * FROM Urban WHERE defid=:urbanId")
    fun getById(urbanId: Long): LiveData<Urban>

    @get:Query("SELECT count(*) FROM Urban")
    val count: Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg urban: Urban)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(urban: Urban)

}