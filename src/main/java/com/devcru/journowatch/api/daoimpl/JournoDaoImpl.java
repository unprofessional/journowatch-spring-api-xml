package com.devcru.journowatch.api.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.devcru.journowatch.api.dao.JournoDao;
import com.devcru.journowatch.api.objects.Journo;

public class JournoDaoImpl implements JournoDao {

	protected JdbcTemplate template;

	@Autowired
	@Qualifier("dataSource")
	public void setDataSource(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}
	
	ResultSetExtractor<String> rse = new ResultSetExtractor<String>() {
		@Override
		public String extractData(ResultSet rs) throws SQLException, DataAccessException {
			
			if(rs.next()) return rs.getString(1);
			else return null;
			
		}
	};

	@Override
	public boolean addJourno(Journo journo) {
		boolean isSuccess = false;
		String sql = "INSERT INTO journos (column, column) VALUES (?, ?)";
		
		String name = journo.getName();
		String[] publications = journo.getPublications();
		int overallRating = journo.getOverallScore();
		String status = journo.getStatus();
		String bio = journo.getBio();
		
		try {
			template.update(sql, new Object[]{name, publications, overallRating, status, bio});
			isSuccess = true;
		} catch (DataAccessException e) {
			isSuccess = false;
			e.printStackTrace();
		}
		
		return isSuccess;
	}

	@Override
	public Journo getJourno(Journo journo) {
		String sql = "SELECT * FROM journos WHERE name = ?";
		
		String name = journo.getName();
		// TODO: ^^^ Should use UUID... in fact, change the getUser() method in User to fix this
		
		List<Map<String,Object>> rows = null;
		
		try {
			rows = template.queryForList(sql, new Object[]{name});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		// Debug
//		for(int i = 0; i < rows.size(); i++) {
//			System.out.println("rows.get(i): " + rows.get(i));
//		}
		
		for(Map<String, Object> row : rows) {
			journo.setName((String)row.get("name"));
			journo.setPublications((String[])row.get("publications"));
			journo.setOverallScore((int)row.get("overallscore"));
			journo.setStatus((String)row.get("status"));
			journo.setBio((String)row.get("bio"));
		}
		
		return journo;
	}

	@Override
	public boolean updateJourno(Journo journo) {
		boolean isSuccess = false; 
		String sql = "UPDATE journo (name, publications, status, bio) VALUES (?, ?, ?, ?)";
		
		String name = journo.getName();
		String publications = null;
		String status = null;
		String bio = null;
		// Update the overallrating via another, dedicated method
		
		try {
			template.update(sql, new Object[]{name, publications, status, bio});
			isSuccess = true;
		} catch (DataAccessException e) {
			isSuccess = false;
			e.printStackTrace();
		}
		
		return isSuccess;
	}

	@Override
	public boolean deleteJourno(Journo journo) {
		// TODO Auto-generated method stub
		return false;
	}

}
