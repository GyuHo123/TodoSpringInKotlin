package com.example.todo.repository

import com.example.todo.domain.TodoEntity
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository

@Repository
interface TodoRepository : JpaRepository<TodoEntity, Long?> {
    override fun findAll():List<TodoEntity>
}
