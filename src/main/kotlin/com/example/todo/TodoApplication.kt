package com.example.todo

import TodoRepository
import com.example.todo.domain.TodoDTO
import com.example.todo.domain.toDTO
import com.example.todo.domain.toEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import java.time.LocalDate

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(basePackages = ["com.example.todo.repository"])
class TodoApplication {

	@Autowired
	lateinit var repository: TodoRepository

	fun run() {
		val todoDTO = TodoDTO(
			id = 1,
			name = "Create a Spring Todo Project",
			description = "Follow the steps in the tutorial to create a Spring Todo project.",
			dueDate = LocalDate.now().plusDays(7),
			isCompleted = false
		)

		val createdTodo = repository.save(todoDTO.toEntity()).toDTO()

		if (createdTodo != null) {
			println("Created Todo: ${createdTodo.name}")
		} else {
			println("Failed to create Todo.")
		}

		val allTodos = repository.findAllTodos()

		allTodos.forEach {
			println("Todo: ${it.name}")
		}
	}
}

fun main(args: Array<String>) {
	val context: ApplicationContext = SpringApplication.run(TodoApplication::class.java, *args)
	val app = context.getBean(TodoApplication::class.java)
	app.run()
}

