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

import com.devcru.journowatch.api.dao.PartnershipDao;
import com.devcru.journowatch.api.objects.Partnership;

public class PartnershipDaoImpl implements PartnershipDao {
	
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
	
	ResultSetExtractor<Object> rseObject = new ResultSetExtractor<Object>() {
		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			return (rs.next() ? rs.getObject(1) : null);
		}
	};

	@Override
	public boolean createPartnership(Partnership partnership) {
		boolean isSuccess = false;
		
		String sql = "INSERT INTO partnerships (journouuid, venueuuid, type) VALUES (?, ?, ?)";
		
		UUID journouuid = partnership.getJournouuid();
		UUID venueuuid = partnership.getVenueuuid();
		int type = partnership.getType();
		
		try {
			template.update(sql, new Object[]{journouuid, venueuuid, type});
			isSuccess = true;
		} catch (DataAccessException e) {
			isSuccess = false;
			e.printStackTrace();
		}
		
		return isSuccess;
	}

	@Override
	public Partnership getPartnership(Partnership partnership) {

		UUID uuid = partnership.getUuid();
		UUID juuid = partnership.getJournouuid();
		UUID vuuid = partnership.getVenueuuid();
		
		String sqlUuid = "SELECT * FROM partnerships WHERE uuid = ?";
		String sqlJoinUuid = "SELECT * FROM partnerships WHERE journouuid = ? AND venueuuid = ?";
		
		List<Map<String, Object>> rows = null;
		
		try {
			rows = template.queryForList(
				(uuid != null ? sqlUuid : sqlJoinUuid),
				(uuid != null ? new Object[]{uuid} : new Object[]{juuid, vuuid})
			);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		for(Map<String, Object> row : rows) {
			partnership.setUuid((UUID)row.get("uuid"));
			partnership.setJournouuid((UUID)row.get("journouuid"));
			partnership.setVenueuuid((UUID)row.get("venueuuid"));
			partnership.setType((int)row.get("type"));
		}
		
		return partnership;
	}

	@Override
	public boolean updatePartnership(Partnership partnership) {
		boolean isSuccess = false;
		
		String sql = "UPDATE partnerships SET journouuid = ?, venueuuid = ?, type = ?";
		
		UUID journouuid = partnership.getJournouuid();
		UUID venueuuid = partnership.getVenueuuid();
		int type = partnership.getType();
		
		try {
			template.update(sql, new Object[]{journouuid, venueuuid, type});
			isSuccess = true;
		} catch (DataAccessException e) {
			isSuccess = false;
			e.printStackTrace();
		}
		
		return isSuccess;
	}

	@Override
	public boolean deletePartnership(Partnership partnership) {
		boolean isSuccess = false;
		
		String sql = "DELETE FROM partnerships WHERE uuid = ?";
		
		UUID uuid = partnership.getUuid();
		
		try {
			template.update(sql, new Object[]{uuid});
			isSuccess = true;
		} catch (DataAccessException e) {
			isSuccess = false;
			e.printStackTrace();
		}
		
		return isSuccess;
	}
	
	/* Supporting */
	
	/*
	 * (non-Javadoc)
	 * Gets all partnership records for a venue via the venueuuid
	 * i.e. to show us all journos who have worked for a venue in any capacity
	 */
	@Override
	public LinkedList<Partnership> getAllPartnerships(Partnership partnership) {
		
		String sqlJournoUuid = "SELECT * FROM partnerships WHERE journouuid = ?";
		String sqlVenueUuid = "SELECT * FROM partnerships WHERE venueuuid = ?";
		
		UUID journouuid = partnership.getJournouuid();
		UUID venueuuid = partnership.getVenueuuid();
		
		List<Map<String, Object>> rows = null;
		
		try {
			rows = template.queryForList(
				(journouuid != null ? sqlJournoUuid : sqlVenueUuid),
				(journouuid != null ? journouuid : venueuuid)
			);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		LinkedList<Partnership> partnerships = new LinkedList<Partnership>();
		
		for(Map<String, Object> row : rows) {
			Partnership pship = new Partnership();
			pship.setUuid((UUID)row.get("uuid"));
			pship.setJournouuid((UUID)row.get("journouuid"));
			pship.setVenueuuid((UUID)row.get("venueuuid"));
			pship.setType((int)row.get("type"));
			partnerships.add(pship);
		}
		
		return partnerships;
	}

}
