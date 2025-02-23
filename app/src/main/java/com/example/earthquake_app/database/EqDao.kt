package com.example.earthquake_app.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.earthquake_app.Earthquake

interface EqDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(eqList:MutableList<Earthquake>)

    @Query("SELECT * FROM earthquakes")
    fun getAllEarthquake():MutableList<Earthquake>
}