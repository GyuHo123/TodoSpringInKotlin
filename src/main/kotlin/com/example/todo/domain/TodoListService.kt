package com.example.todo.domain

import com.example.todo.repository.TodoRepository
import org.springframework.stereotype.Service

@Service
class TodoListService(private val repository: TodoRepository) {
    fun getAllTodos(): List<TodoDTO> {
        return repository.findAll().map { it.toDTO() }
    }

    fun getTodoById(id: Long): TodoDTO {
        val todo = repository.findById(id).orElseThrow { TodoNotFoundException() }
        return todo.toDTO()
    }

    fun createTodo(todoDTO: TodoDTO): TodoDTO {
        val entity = todoDTO.toEntity()
        val savedEntity = repository.save(entity)
        return savedEntity.toDTO()
    }

    fun updateTodoById(id: Long, todoDTO: TodoDTO): TodoDTO {
        val entity = repository.findById(id).orElseThrow { TodoNotFoundException() }
        entity.dueDate = todoDTO.dueDate
        entity.isCompleted = todoDTO.isCompleted
        entity.description = todoDTO.description
        entity.name = todoDTO.name
        val updatedEntity = repository.save(entity)
        return updatedEntity.toDTO()
    }

    fun deleteTodoById(id: Long) {
        if (!repository.existsById(id)) throw TodoNotFoundException()
        repository.deleteById(id)
    }
}

class TodoNotFoundException : RuntimeException("Todo not found")