package kr.or.connect.todo.persistence;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class TodoDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private BeanPropertyRowMapper<Todo> rowMapper;
	
	public TodoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("todo")
				.usingGeneratedKeyColumns("id");
		rowMapper = new BeanPropertyRowMapper<>(Todo.class);
	}
	
	public Integer insert(Todo todo){
		try{
			SqlParameterSource params = new BeanPropertySqlParameterSource(todo);

			System.out.println(todo + "/" + params);
			return insertAction.executeAndReturnKey(params).intValue();
		}
		catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
	
	public List<Todo> getList(){
		Map<String, String> param = Collections.EMPTY_MAP;
		try{
			return jdbc.query(TodoSqls.SELECT_ALL, param, rowMapper);
		}
		catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
	
	public Integer getAllCount(){
		Map<String, Integer> param = Collections.EMPTY_MAP;
		return jdbc.queryForObject(TodoSqls.SELECT_ALL_COUNT, param, Integer.class);
	}
	
	public Integer complete(Todo todo){
		Map<String, Object> param = new HashMap<String, Object>();
		System.out.println(todo.isCompleted());
		param.put("completed", todo.isCompleted());
		param.put("id", todo.id);
		return jdbc.update(TodoSqls.UPDATE_COMPLETE, param);
	}
	
	public Integer delete(Todo todo){
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("id", todo.id);
		return jdbc.update(TodoSqls.DELETE_BY_ID, param);
	}
	
	public Integer getTodoCount(){
		Map<String, Integer> param = Collections.EMPTY_MAP;
		return jdbc.queryForObject(TodoSqls.SELECT_ALL_COUNT, param, Integer.class);
	}
	
	public List<Todo> getListByComplete(boolean completed){
		Map<String, Boolean> param = new HashMap<String, Boolean>();
		param.put("completed", completed);
		return jdbc.query(TodoSqls.SELECT_BY_COMPLETE, param, rowMapper);
	}
	
	public Integer deleteCompleteAll(){
		Map param = Collections.EMPTY_MAP;
		return jdbc.update(TodoSqls.DELETE_BY_COMPLETE, param);
	}
	
}
