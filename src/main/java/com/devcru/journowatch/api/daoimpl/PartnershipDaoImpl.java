package com.devcru.journowatch.api.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.devcru.journowatch.api.objects.Journo;
import com.devcru.journowatch.api.objects.Partnership;
import com.devcru.journowatch.api.objects.Venue;

public class PartnershipDaoImpl implements PartnershipDao {
	
	protected JdbcTemplate template;
	
	@Autowired
	@Qualifier("dataSource")
	public void setDataSource(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}
	
	// TODO: Verify if we even need this to cover the getPartnership() method
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
	public Partnership getPartnership(Journo journo, Venue venue) {
		Partnership partnership = new Partnership();
		
		String sql = "SELECT * FROM partnerships WHERE journouuid = ? AND venueuuid = ?";

		UUID journouuid = journo.getUuid();
		UUID venueuuid = venue.getUuid();
		
		List<Map<String, Object>> rows = null;
		
		try {
			rows = template.queryForList(sql, new Object[]{journouuid, venueuuid});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		for(Map<String, Object> row : rows) {
			partnership.setUuid((UUID)row.get("uuid"));
			partnership.setJournouuid((UUID)row.get("journouuid"));
			partnership.setVenueuuid((UUID)row.get("venueuuid"));
			partnership.setType((int)row.get("type")); // use Integer class instead???
		}
		
		return partnership;
	}
	
	// TODO: This should be the "TRUE way"
	@Override
	public Partnership getPartnershipViaUuid(Partnership partnership) {
		
		String sql = "SELECT * FROM partnerships WHERE uuid = ?";
		UUID uuid = partnership.getUuid();
		
		List<Map<String, Object>> rows = null;
		
		try {
			rows = template.queryForList(sql, new Object[]{uuid});
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

}
