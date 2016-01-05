package com.devcru.journowatch.api.daoimpl;

import com.devcru.journowatch.api.dao.UserDao;
import com.devcru.journowatch.api.objects.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.sql.DataSource;

/**
 * Created by Monitored on 12/25/2015. Implementation for UserDao
 */

public class UserDaoImpl implements UserDao {

	// Use LOCAL SCOPE later

	private String loginSql = "";
	private String rateVenueSql = "";
	private String rateJourno = "";

	protected JdbcTemplate template;

	@Autowired
	@Qualifier("dataSource")
	public void setDataSource(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}
	

	// To be used for all query() calls since they allow for possible null returns
	// whereas queryForWhatever() does not
	ResultSetExtractor<String> rse = new ResultSetExtractor<String>() {
		@Override
		public String extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			return (rs.next() ? rs.getString(1) : null);
		}
	};

	public void login() {
		template.execute(loginSql);
	}

	public void rateVenue() {
		template.execute(rateVenueSql);
	}

	public void rateJourno() {
		template.execute(rateJourno);
	}
	
	/* CRUD Operations */

	@Override
	public boolean addUser(User user) {
		boolean isSuccess = false;
		String sql = "INSERT INTO users (username, email, firstname, lastname, role, password) VALUES (?, ?, ?, ?, ?, ?)";
		
		String username = user.getUsername();
		String email = user.getEmail();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String role = user.getRole();
		String password = user.getPassword();
		
		try {
			template.update(sql, username, email, firstName, lastName, role, password);
			isSuccess = true;
		} catch (DataAccessException e) {
			isSuccess = false;
			e.printStackTrace();
		}
		
		return isSuccess;
		
	}

	@Override
	public User getUserByUsername(User user) {
		
		//boolean isSuccess = false;
		String sql = "SELECT uuid, username, email, firstname, lastname FROM users WHERE username = ?";
		List<Map<String, Object>> rows = null;
		
		String username = user.getUsername();
		
		try {
			rows = template.queryForList(sql, new Object[]{username}, rse);
			//isSuccess = true;
		} catch (DataAccessException e) {
			//isSuccess = false;
			e.printStackTrace();
		}
		
		// Debug
		for(int i = 0; i < rows.size(); i++) {
			System.out.println("rows.get(i): " + rows.get(i));
		}
		
		for(Map<String, Object> row : rows) {
			user.setUuid((UUID)row.get("uuid"));
			user.setEmail((String)row.get("email"));
			user.setUsername((String)row.get("username"));
			user.setFirstName((String)row.get("firstName"));
			user.setLastName((String)row.get("lastName"));
			user.setRole((String)row.get("role"));
			user.setPassword((String) "REDACTED"); // Is this necessary?
		}
		
		return user;		
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		
	}

}
