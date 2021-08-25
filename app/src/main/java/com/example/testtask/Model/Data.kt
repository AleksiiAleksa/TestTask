package com.example.testtask

data class Data(
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val full_name: String? = null,
    val owner: Owner? = null
)
data class Owner(
    val login: String? = null,
)

