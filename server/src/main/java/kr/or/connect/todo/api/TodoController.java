package kr.or.connect.todo.api;

import java.util.Collection;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.todo.persistence.Todo;
import kr.or.connect.todo.service.TodoService;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
	public TodoService service;

	public TodoController(TodoService service) {
		this.service = service;
	}

	@GetMapping("/Test")
	public Integer gg() {
		return service.getCount();
	}

	@PutMapping("/insert")
	public Integer insert(@RequestBody Todo todo) {
		return service.insert(todo);
	}

	@GetMapping("/getlist")
	public Collection<Todo> getList() {
		return service.getList();
	}

	@PostMapping("/complete")
	public Integer complete(@RequestBody Todo todo) {
		return service.complete(todo);
	}

	@DeleteMapping("/delete")
	public Integer delete(@RequestBody Todo todo) {
		return service.delete(todo);
	}
	
	@GetMapping("/getcount")
	public Integer getCount(){
		return service.getCount();
	}
	
	@PostMapping("/getlistbycomplete")
	public List<Todo> getListByComplete(@RequestBody Todo todo){
		return service.getByComplete(todo.isCompleted());
	}

	@DeleteMapping("/deletecomplete")
	public Integer DeleteComplete(){
		return service.deleteComplete();
	}
}
