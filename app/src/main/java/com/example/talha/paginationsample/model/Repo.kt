package com.example.talha.paginationsample.model

data class Repo(
        val id: Long,
        val name: String,
        val full_name: String,
        val description: String?,
        val url: String
) {
}