package com.example.todo.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
class TodoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var name: String,
    var description: String,
    var dueDate: LocalDate,
    var isCompleted: Boolean
)
fun TodoEntity.toDTO() = TodoDTO(id, name, description, dueDate, isCompleted)

fun TodoDTO.toEntity() = TodoEntity(id, name, description, dueDate, isCompleted)
