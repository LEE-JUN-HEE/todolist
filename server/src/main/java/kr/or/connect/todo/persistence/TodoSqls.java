package kr.or.connect.todo.persistence;

public class TodoSqls {
	static final String DELETE_BY_ID = "DELETE FROM todo WHERE id= :id";
	static final String SELECT_ALL = "Select id, todo, completed from todo ORDER BY date DESC";
	static final String SELECT_ALL_COUNT = "Select Count(*) from todo";
	static final String UPDATE_COMPLETE = "UPDATE todo Set completed = :completed where id = :id";
	static final String SELECT_BY_COMPLETE_COUNT = "SELECT count(*) from todo where completed = false";
	static final String SELECT_BY_COMPLETE = "SELECT * from todo where completed = :completed ORDER BY date DESC";
	static final String DELETE_BY_COMPLETE = "DELETE from todo where completed = true";
}
