package com.devcru.journowatch.api.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
	
	// To be used for all query() calls since they allow for possible null returns
	// whereas queryForWhatever() does not
	ResultSetExtractor<String> rse = new ResultSetExtractor<String>() {
		@Override
		public String extractData(ResultSet rs) throws SQLException, DataAccessException {
			
			if(rs.next()) return rs.getString(1);
			else return null;
			
		}
	};
	
	/* CRUD operations */

	@Override
	public boolean createJourno(Journo journo) {
		boolean isSuccess = false;
		
		String sql = "INSERT INTO journos (fullname, overallscore, status, bio) VALUES (?, ?, ?, ?)";
		
		String fullname = journo.getFullname();
		int overallscore = journo.getOverallScore();
		int status = journo.getStatus();
		String bio = journo.getBio();
		
		try {
			template.update(sql, new Object[]{fullname, overallscore, status, bio});
			isSuccess = true;
		} catch (DataAccessException e) {
			isSuccess = false;
			e.printStackTrace();
		}
		
		return isSuccess;
	}

	@Override
	public Journo getJourno(Journo journo) {
		String sql = "SELECT * FROM journos WHERE fullname = ?";
		// TODO: Should use UUID or username (see approach for users)
		
		UUID uuid = journo.getUuid();
		String fullname = journo.getFullname();
		
		List<Map<String,Object>> rows = null;
		
		try {
			rows = template.queryForList(sql, new Object[]{fullname});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		// Debug
//		for(int i = 0; i < rows.size(); i++) {
//			System.out.println("rows.get(i): " + rows.get(i));
//		}
		
		for(Map<String, Object> row : rows) {
			journo.setFullname((String)row.get("fullname"));
			journo.setOverallScore((int)row.get("overallscore"));
			journo.setStatus((int)row.get("status"));
			journo.setBio((String)row.get("bio"));
		}
		
		return journo;
	}

	@Override
	public boolean updateJourno(Journo journo) {
		boolean isSuccess = false; 
		String sql = "UPDATE journos SET fullname = ?, status = ?, bio = ?, overallscore = ? WHERE uuid = ?";
		
		UUID uuid = journo.getUuid();
		String fullname = journo.getFullname();
		int status = journo.getStatus();
		String bio = journo.getBio();
		int overallscore = journo.getOverallScore();
		
		try {
			template.update(sql, new Object[]{fullname, status, bio, overallscore, uuid});
			isSuccess = true;
		} catch (DataAccessException e) {
			isSuccess = false;
			e.printStackTrace();
		}
		
		return isSuccess;
	}

	@Override
	public boolean deleteJourno(Journo journo) {
		boolean isSuccess = false;
		
		String sql = "DELETE FROM journos WHERE uuid = ?";
		
		UUID uuid = journo.getUuid();
		
		try {
			template.update(sql, new Object[]{uuid});
			isSuccess = true;
		} catch (DataAccessException e) {
			isSuccess = false;
			e.printStackTrace();
		}
		
		return isSuccess;
	}

	@Override
	public LinkedList<Journo> getAllJournos() {
		String sql = "SELECT * FROM journos";
		
		LinkedList<Journo> journos = new LinkedList<Journo>();
		List<Map<String,Object>> rows = null;
		
		try {
			rows = template.queryForList(sql);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		for(Map<String,Object> row : rows) {
			Journo journo = new Journo();
			journo.setUuid((UUID)row.get("uuid"));
			journo.setFullname((String)row.get("fullname"));
			journo.setStatus((int)row.get("status"));
			journo.setOverallScore((int)row.get("overallscore"));
			journo.setBio((String)row.get("bio"));
		}
		
		return journos;
	}

}
