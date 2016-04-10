package com.devcru.journowatch.api.daoimpl;

import com.devcru.journowatch.api.dao.UserDao;
import com.devcru.journowatch.api.objects.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
		public String extractData(ResultSet rs) throws SQLException, DataAccessException {
			return (rs.next() ? rs.getString(1) : null);
		}
	};
	
	ResultSetExtractor<Object> rseObject = new ResultSetExtractor<Object>() {
		@Override
		public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
			return (rs.next() ? rs.getObject(1) : null);
		}
	};
	
	ResultSetExtractor<User> rseUser = new ResultSetExtractor<User>() {
		@Override
		public User extractData(ResultSet rs) throws SQLException, DataAccessException {
			return (rs.next() ? (User) rs.getObject(1) : null);
		}
	};
	
	ResultSetExtractor<UUID> rseUuid = new ResultSetExtractor<UUID>() {
		@Override
		public UUID extractData(ResultSet rs) throws SQLException, DataAccessException {
			return (rs.next() ? (UUID) rs.getObject(1) : null);
		}
	};
	
	/* CRUD Operations */

	@Override
	public boolean createUser(User user) {
		boolean isSuccess = false;
		String sql = "INSERT INTO users (username, email, firstname, lastname, role, password) VALUES (?, ?, ?, ?, ?, ?)";
		
		String username = user.getUsername();
		String email = user.getEmail();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		int role = user.getRole();
		String password = user.getPassword();
		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(password);
		
		Object[] attributes = new Object[]{username, email, firstName, lastName, role, encodedPassword};
		for(int i = 0; i < attributes.length; i++) {
			System.out.println("attributes[i]: " + attributes[i]);
			System.out.println("attributes[i].getClass(): " + attributes[i].getClass());
		}
		
		try {
			template.update(sql, attributes);
			isSuccess = true;
		} catch (DataAccessException e) {
			isSuccess = false;
			e.printStackTrace();
		}
		
		return isSuccess;
		
	}

	@Override
	public User getUser(User user) {
		
		// This works because uuid, username, and email are all unique
		// As long as we know one, we can find the rest
		
		String sqlUuid = "SELECT uuid, username, email, firstname, lastname, role FROM users WHERE uuid = ?";
		String sqlUsername = "SELECT uuid, username, email, firstname, lastname, role FROM users WHERE username = ?";
		String sqlEmail = "SELECT uuid, username, email, firstname, lastname, role FROM users WHERE email = ?";
		
		UUID uuid = user.getUuid();
		String username = user.getUsername();
		String email = user.getEmail();
		
		String sql = uuid == null ? ( username == null ? sqlEmail : sqlUsername) : sqlUuid;
		
		if(email == null) {
			System.out.println("email is null, no identifiable user data to query with!");
		} // TODO: In which case maybe not even run the query
		
		List<Map<String, Object>> rows = null;
		
		Object[] field = {
				uuid == null ? ( username == null ? email : username) : uuid
		};
		
		try {
			rows = template.queryForList(sql, field);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		// Debug
//		for(int i = 0; i < rows.size(); i++) {
//			System.out.println("rows.get(i): " + rows.get(i));
//		}
		
		for(Map<String, Object> row : rows) {
			user.setUuid((UUID)row.get("uuid"));
			user.setUsername((String)row.get("username"));
			user.setEmail((String)row.get("email"));
			user.setFirstName((String)row.get("firstname"));
			user.setLastName((String)row.get("lastname"));
			user.setRole((int)row.get("role"));
			user.setPassword((String) "REDACTED"); // Is this necessary?  Might just come back as null otherwise
		}
		
		return user;		
	}
	
	@Override
	public boolean updateUser(User user) {
		boolean isSuccess = false;
		String sql = "UPDATE users SET email = ?, firstname = ?, lastname = ?, role = ? WHERE username = ?";
		
		String email = user.getEmail();
		String firstname = user.getFirstName();
		String lastname = user.getLastName();
		int role = user.getRole();
		String username = user.getUsername();
		
		Object[] attributes = new Object[]{email, firstname, lastname, role, username};
		
		// DEBUG
//		for(int i = 0; i < attributes.length; i++) {
//			System.out.println("attributes[i]: " + attributes[i]);
//			System.out.println("attributes[i].getClass(): " + attributes[i].getClass());
//		}
		
		try {
			template.update(sql, attributes);
			isSuccess = true;
		} catch (DataAccessException e) {
			isSuccess = false;
			e.printStackTrace();
		}
		
		return isSuccess;
	}

	@Override
	public boolean deleteUser(User user) {
		boolean isSuccess = false;
		String sql = "DELETE FROM users WHERE username = ?";
		
		// Delete completed tasks, returning full details of the deleted rows:
		// DELETE FROM tasks WHERE status = 'DONE' RETURNING *;
		
		String username = user.getUsername();
		
		try {
			template.update(sql, new Object[]{username});
			isSuccess = true;
		} catch (DataAccessException e) {
			isSuccess = false;
			e.printStackTrace();
		}
		
		return isSuccess;
	}
	
	/* Helper methods */
	
	@Override
	public boolean verifyCredentials(User user) {
		boolean isSuccess = false;
		
		String sql = "SELECT uuid FROM user WHERE username = ? AND password = ?";
		
    	UUID uuid = user.getUuid();
    	String username = user.getUsername();
    	String password = user.getPassword();
    	
    	// TODO: password encryption should happen here
    	// String cryptPass = encryptPass(password);
    	// then pass the encrypted pass as the SQL query password param
    	
    	uuid = template.query(sql, new Object[]{username, password}, rseUuid);
    	
    	if(null != uuid) {
    		isSuccess = true;
    	} else isSuccess = false;
		
		return isSuccess;
	}
	
	/* FIXME: The follow three methods  should go elsewhere */

	@Override
	public void rateVenue() {
		String rateVenueSql = "";
		template.execute(rateVenueSql);
	}

	@Override
	public void rateJourno() {
		String rateJourno = "";
		template.execute(rateJourno);
	}

}
