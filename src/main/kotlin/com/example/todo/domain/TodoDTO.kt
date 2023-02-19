package com.example.todo.domain

import java.time.LocalDate

data class TodoDTO(
    val id: Long?,
    val name: String,
    val description: String,
    val dueDate: LocalDate,
    val isCompleted: Boolean
)
