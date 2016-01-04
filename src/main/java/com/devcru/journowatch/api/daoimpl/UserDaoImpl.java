package com.devcru.journowatch.api.daoimpl;

import com.devcru.journowatch.api.dao.UserDao;
import com.devcru.journowatch.api.objects.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

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
			isSuccess = true;
			template.update(sql, username, email, firstName, lastName, role, password);
		} catch (DataAccessException e) {
			isSuccess = false;
			e.printStackTrace();
		}
		
		return isSuccess;
		
	}

	@Override
	public void getUser(User user) {
		// TODO Auto-generated method stub
		
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
