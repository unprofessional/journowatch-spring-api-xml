package com.devcru.journowatch.api.daoimpl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import com.devcru.journowatch.api.dao.JournoDao;
import com.devcru.journowatch.api.objects.Journo;

public class JournoDaoImpl implements JournoDao {

	protected JdbcTemplate template;

	@Autowired
	@Qualifier("dataSource")
	public void setDataSource(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}

	@Override
	public boolean addJourno(Journo journo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Journo getJourno(Journo journo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateJourno(Journo journo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteJourno(Journo journo) {
		// TODO Auto-generated method stub
		return false;
	}

}
