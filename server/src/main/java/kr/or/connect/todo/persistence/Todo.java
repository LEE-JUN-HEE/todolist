package kr.or.connect.todo.persistence;

import java.sql.Timestamp;

public class Todo {
	Integer id;
	String todo;
	Boolean completed;
	Timestamp date;
	
	public Todo(){
		id = null;
		todo = null;
		completed = false;
		date = null;
	}
	
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTodo() {
		return todo;
	}
	public void setTodo(String todo) {
		this.todo = todo;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
	@Override
	public String toString(){
		return this.id + "/" + this.todo + "/" + "/" + this.completed + "/" + this.date;
	}
}
