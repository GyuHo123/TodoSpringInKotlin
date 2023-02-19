import com.example.todo.domain.TodoDTO
import com.example.todo.domain.toDTO
import com.example.todo.domain.toEntity
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Service

@ComponentScan("com.example.todo.domain")
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
        val entity = repository.findById(id).orElseThrow { TodoNotFoundException() }.apply {
            name = todoDTO.name
            description = todoDTO.description
            dueDate = todoDTO.dueDate
            isCompleted = todoDTO.isCompleted
        }
        val updatedEntity = repository.save(entity)
        return updatedEntity.toDTO()
    }

    fun deleteTodoById(id: Long) {
        if (!repository.existsById(id)) throw TodoNotFoundException()
        repository.deleteById(id)
    }
}

class TodoNotFoundException : RuntimeException("Todo not found")