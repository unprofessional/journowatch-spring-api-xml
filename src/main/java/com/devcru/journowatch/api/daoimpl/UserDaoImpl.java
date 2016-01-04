package com.devcru.journowatch.api.daoimpl;

import com.devcru.journowatch.api.dao.UserDao;
import com.devcru.journowatch.api.objects.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by Monitored on 12/25/2015. Implementation for UserDao
 */

public class UserDaoImpl implements UserDao {

	// Use CONSTANTS later

	private String loginSql = "";
	private String rateVenueSql = "";
	private String rateJourno = "";
	
	private String addSql = "";

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
	public void addUser(User user) {
		String username = user.getUsername();
		String email = user.getEmail();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String role = user.getRole();
		String password = user.getPassword();
		template.update(addSql, username, email, firstName, lastName, role, password);
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
