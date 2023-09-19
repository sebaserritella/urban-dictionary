package com.urbandictionary.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.urbandictionary.data.database.entities.UrbanEntity
import com.urbandictionary.domain.database.DaoInterface

@Dao
interface UrbanDao : DaoInterface {

    @Query("SELECT * FROM UrbanEntity")
    fun all(): List<UrbanEntity>

    @Query("SELECT * FROM UrbanEntity WHERE searchWord=:word")
    fun getDefine(word: String): List<UrbanEntity>

    @get:Query("SELECT count(*) FROM UrbanEntity")
    val count: Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllUrban(urban: List<UrbanEntity>)

    @Query("SELECT * FROM UrbanEntity WHERE word = :word ORDER BY thumbs_up")
    fun getDefineOrderByUp(word: String): LiveData<List<UrbanEntity>>

    @Query("SELECT * FROM UrbanEntity WHERE word = :word ORDER BY thumbs_down")
    fun getDefineOrderByDown(word: String): LiveData<List<UrbanEntity>>

}