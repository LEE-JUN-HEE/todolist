package kr.or.connect.todo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import kr.or.connect.todo.persistence.Todo;
import kr.or.connect.todo.persistence.TodoDao;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class TodoTest {
	@Autowired
	private TodoDao dao;
	
	@Test
	public void getList(){
		List<Todo> list = dao.getList();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	@Test
	public void HI(){
		System.out.println("junittest");
	}
}
