package com.example.talha.paginationsample.db

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query


@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepo(repo:ArrayList<RepoEntity>)

    @Query("SELECT * FROM RepoData")
    fun reposByName(): DataSource.Factory<Int,RepoEntity>


}