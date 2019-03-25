package com.example.talha.paginationsample.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "RepoData")
class RepoEntity(
        @PrimaryKey
        var id:Long,
        val name: String,
        val full_name: String,
        val description: String?,
        val url: String
) {
}