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

import com.devcru.journowatch.api.dao.VenueDao;
import com.devcru.journowatch.api.objects.Venue;

public class VenueDaoImpl implements VenueDao {
	
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
			return rs.next() ? rs.getString(1) : null;
		}
	};
	
	/* CRUD Operations */

	@Override
	public boolean createVenue(Venue venue) {
		boolean isSuccess = false;
		
		String sql = "INSERT INTO venues (name, status, overallscore, bio) VALUES (?, ?, ?, ?)";
		
		String name = venue.getName();
		int status = venue.getStatus();
		int overallscore = venue.getOverallscore();
		String bio = venue.getBio();
		
		try {
			template.update(sql, new Object[]{name, status, overallscore, bio});
			isSuccess = true;
		} catch (DataAccessException e) {
			isSuccess = false;
			e.printStackTrace();
		}
		
		return isSuccess;
	}

	@Override
	public Venue getVenue(Venue venue) {
		
		// XXX: Caveats: name is assumed to be unique.  Enforce this in the DB constraints!!
		
		/**
		 * TODO: DO THIS FOR ALL OTHER DAOIMPLs!!!!
		 */
		
		String sqlUuid = "SELECT * FROM venues WHERE uuid = ?";
		String sqlName = "SELECT * FROM venue WHERE name = ?";
		
		UUID uuid = venue.getUuid();
		String name = venue.getName();
		
		List<Map<String, Object>> rows = null;
		
		// If no uuid, use name, else use uuid
		String sql = uuid == null ? sqlName : sqlUuid;
		Object[] field= {uuid == null ? name : uuid};
		
		try {
			rows = template.queryForList(sql, field);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		for(Map<String, Object> row : rows) {
			venue.setUuid((UUID)row.get("uuid"));
			venue.setName((String)row.get("name"));
			venue.setStatus((int)row.get("status"));
			venue.setOverallscore((int)row.get("overallscore"));
			venue.setBio((String)row.get("bio"));
		}
		
		return venue;
	}

	@Override
	public boolean updateVenue(Venue venue) {
		boolean isSuccess = false;
		
		String sql = "UPDATE venues SET name = ?, status = ?, overallscore = ?, bio = ? WHERE uuid = ?";
		
		String name = venue.getName();
		int status = venue.getStatus();
		int overallscore = venue.getOverallscore();
		String bio = venue.getBio();
		UUID uuid = venue.getUuid();
		
		try {
			template.update(sql, new Object[]{name, status, overallscore, bio, uuid});
			isSuccess = true;
		} catch (DataAccessException e) {
			isSuccess = false;
			e.printStackTrace();
		}
		
		return isSuccess;
	}

	@Override
	public boolean deleteVenue(Venue venue) {
		boolean isSuccess = false;
		
		String sql = "DELETE FROM venues WHERE uuid = ?";
		
		UUID uuid = venue.getUuid();
		
		try {
			template.update(sql, new Object[]{uuid});
			isSuccess = true;
		} catch (DataAccessException e) {
			isSuccess = false;
			e.printStackTrace();
		}
		
		return isSuccess;
	}
	
	/* Helper methods */
	
	@Override
	public LinkedList<Venue> getAllVenues() {
		LinkedList<Venue> venues = new LinkedList<Venue>();
		
		String sql = "SELECT * FROM venues";
		// TODO: Need to LIMIT for pagination... find out more later
		
		List<Map<String, Object>> rows = null;
		
		try {
			rows = template.queryForList(sql);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		for(Map<String, Object> row : rows) {
			Venue venue = new Venue();
			
			venue.setUuid((UUID)row.get("uuid"));
			venue.setName((String)row.get("name"));
			venue.setStatus((int)row.get("status"));
			venue.setOverallscore((int)row.get("overallscore"));
			venue.setBio((String)row.get("bio"));
			
			venues.add(venue);
		}
		
		return venues;
	}

}
