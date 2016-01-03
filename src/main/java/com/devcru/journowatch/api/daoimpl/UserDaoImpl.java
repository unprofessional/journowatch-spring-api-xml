package com.devcru.journowatch.api.daoimpl;

import com.devcru.journowatch.api.dao.UserDao;
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

}
