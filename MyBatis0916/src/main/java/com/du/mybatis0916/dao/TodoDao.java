package com.du.mybatis0916.dao;

import com.du.mybatis0916.model.Todo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TodoDao {
	
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
	
	private final JdbcTemplate jdbcTemplate;
	
	public TodoDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private static class TodoRowMapper implements RowMapper<Todo> {
		public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
			Todo todo = new Todo();
			todo.setId(rs.getInt("id"));
			todo.setTitle(rs.getString("title"));
			todo.setCompleted(rs.getBoolean("completed"));
			return todo;
		}
	}
	
	public List<Todo> findAll() {
		String sql= "select * from todos";
		return jdbcTemplate.query(sql, new TodoRowMapper());
	}
	
	public void add(String title) {
		String sql = "insert into todos (title) values (?)";
		jdbcTemplate.update(sql, title);
	}
	
	public void delete(int id) {
		String sql = "delete from todos where id = ?";
		jdbcTemplate.update(sql, id);
	}
	
	public void toggleCompleted(int id) {
		String sql = "update todos set completed = not completed where id = ?";
		jdbcTemplate.update(sql, id);
	}
}
