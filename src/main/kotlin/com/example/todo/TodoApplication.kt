package com.example.todo

import com.example.todo.repository.TodoRepository
import com.example.todo.domain.TodoDTO
import com.example.todo.domain.toEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import java.time.LocalDate
import com.example.todo.controller.TodoListController
@SpringBootApplication
class TodoApplication {
	@Autowired
	lateinit var repository: TodoRepository
	@Autowired
	lateinit var todoListController: TodoListController
	fun run() {
		val todoDTO= TodoDTO(
			id = 1,
			name = "Create a Spring Todo Project",
			description = "Follow the steps in the tutorial to create a Spring Todo project.",
			dueDate = LocalDate.now().plusDays(7),
			isCompleted = false
		)

		todoListController.createTodo(todoDTO)

		val todoDTO2= TodoDTO(
			id = 1,
			name = "Create a Spring Todo Project",
			description = "Follow the steps in the tutorial to create a Spring Todo project.",
			dueDate = LocalDate.now().plusDays(7),
			isCompleted = true
		)

		todoListController.updateTodoById(1, todoDTO2)
		repository.save(todoDTO2.toEntity())

		val allTodos = repository.findAll()

		allTodos.forEach {
			println("Todo: ${it.isCompleted}")
		}
	}
}

fun main(args: Array<String>) {
	val context: ApplicationContext = SpringApplication.run(TodoApplication::class.java, *args)
	val app = context.getBean(TodoApplication::class.java)
	app.run()
}
