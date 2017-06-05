package kr.or.connect.todo.service;

import java.sql.Timestamp;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.todo.persistence.Todo;
import kr.or.connect.todo.persistence.TodoDao;

@Service
public class TodoService {
	TodoDao dao;
	
	public TodoService(TodoDao dao){
		this.dao = dao;
	}
	
//	public Integer getCount(){
//		return dao.getCount();
//	}

	
	public Integer insert(Todo todo){
		Timestamp hi = new Timestamp(System.currentTimeMillis());
		todo.setDate(hi);
		String str = new String(todo.getTodo());
		if(str.trim().equals("")){
			return 0;
		}
		else{
			return dao.insert(todo);
		}
	}
	
	public List<Todo> getList(){
		return dao.getList();
	}
	
	public Integer complete(Todo todo){
		return dao.complete(todo);
	}
	
	public Integer delete(Todo todo){
		return dao.delete(todo);
	}
	
	public Integer getCount(){
		return dao.getTodoCount();
	}
	
	public List<Todo> getByComplete(boolean complete){
		return dao.getListByComplete(complete);
	}
	
	public Integer deleteComplete(){
		return dao.deleteCompleteAll();
	}
}
