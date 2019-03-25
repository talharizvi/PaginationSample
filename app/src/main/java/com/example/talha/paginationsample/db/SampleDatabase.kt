package com.example.talha.paginationsample.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(RepoEntity::class),version = 1)
abstract class SampleDatabase:RoomDatabase() {

    abstract fun repoDao(): RepoDao
    companion object {
        private var INSTANCE: SampleDatabase? = null

        fun getInstance(context: Context): SampleDatabase? {

            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SampleDatabase::class.java, "newsdata.db").allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()


                }

            }
            return INSTANCE as SampleDatabase

        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}