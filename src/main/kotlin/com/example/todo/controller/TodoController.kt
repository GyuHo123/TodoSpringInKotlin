package com.example.todo.controller

import com.example.todo.domain.TodoDTO
import com.example.todo.domain.TodoListService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todos")
class TodoListController(private val service: TodoListService) {
	@GetMapping("")
	fun getAllTodos(): ResponseEntity<List<TodoDTO>> {
		val todos = service.getAllTodos()
		return ResponseEntity.ok(todos)
	}

	@GetMapping("/{id}")
	fun getTodoById(@PathVariable("id") id: Long): ResponseEntity<TodoDTO> {
		val todo = service.getTodoById(id)
		return ResponseEntity.ok(todo)
	}

	@PostMapping("")
	fun createTodo(@RequestBody todoDTO: TodoDTO): ResponseEntity<TodoDTO> {
		val createdTodo = service.createTodo(todoDTO)
		return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo)
	}

	@PutMapping("/{id}")
	fun updateTodoById(@PathVariable("id") id: Long, @RequestBody todoDTO: TodoDTO): ResponseEntity<TodoDTO> {
		val updatedTodo = service.updateTodoById(id, todoDTO)
		return ResponseEntity.ok(updatedTodo)
	}

	@DeleteMapping("/{id}")
	fun deleteTodoById(@PathVariable("id") id: Long): ResponseEntity<Unit> {
		service.deleteTodoById(id)
		return ResponseEntity.noContent().build()
	}
}
