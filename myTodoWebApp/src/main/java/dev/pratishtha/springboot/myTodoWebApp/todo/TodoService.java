package dev.pratishtha.springboot.myTodoWebApp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
	
	private static List<Todo> todos = new ArrayList<Todo>();
	
	private static int todoCount = 0;
	
	static {
		todos.add(new Todo(++todoCount, "Pratishtha", "Learn Spring", 
				LocalDate.now().plusWeeks(2), false));
		todos.add(new Todo(++todoCount, "Pratishtha", "Create a Spring project", 
				LocalDate.now().plusWeeks(4), false));
		todos.add(new Todo(++todoCount, "Pratishtha", "Learn Full Stack Development", 
				LocalDate.now().plusMonths(1), false));
	}
	
	public List<Todo> findByUsername (String username) {
		Predicate<? super Todo> predicate = 
				todo -> todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
	}
	
	
	public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
		Todo newTodo = new Todo(++todoCount, username, description, targetDate, done);
		todos.add(newTodo);
	}
	
	public void deleteById(int id) {
//		entering a predicate to check if todo id matches the inout id
		
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
	}


	public Todo findById(int id) {

//		entering a predicate and stream to get the todo id that matches the inout id from list of todos
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}


	public void updateTodo(@Valid Todo todo) {
		deleteById(todo.getId());
		
		todos.add(todo);
	}
}
