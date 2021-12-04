package com.reeta.itunenewproject.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ItunesDbTable::class], version = 2)
abstract class ItunesRoomDatabase : RoomDatabase() {

    abstract fun getResponseFromDao(): AppDao

}