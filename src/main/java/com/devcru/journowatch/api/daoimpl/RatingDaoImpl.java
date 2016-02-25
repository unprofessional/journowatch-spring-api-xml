package com.devcru.journowatch.api.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.devcru.journowatch.api.dao.RatingDao;
import com.devcru.journowatch.api.objects.Rating;

public class RatingDaoImpl implements RatingDao {

	protected JdbcTemplate template;

	@Autowired
	@Qualifier("dataSource")
	public void setDataSource(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}

	ResultSetExtractor<String> rse = new ResultSetExtractor<String>() {
		@Override
		public String extractData(ResultSet rs) throws SQLException, DataAccessException {
			return (rs.next() ? rs.getString(0) : null);
		}
	};

	@Override
	public boolean createRating(Rating rating) {
		
		System.out.println("RDI > reached!");
		
		boolean isSuccess = false;

		String sql = "INSERT INTO ratings (owneruuid, journouuid, score, comment) VALUES (?, ?, ?, ?)";

		UUID owneruuid = rating.getOwneruuid();
		UUID journouuid = rating.getJournouuid();
		int score = rating.getScore();
		String comment = rating.getComment();
		
		System.out.println("RDI > rating.getUuid: " + rating.getUuid());
		System.out.println("RDI > rating.getTimestamp: " + rating.getTimestamp());
		System.out.println("RDI > rating.getOwner: " + rating.getOwneruuid());
		System.out.println("RDI" + rating.getJournouuid());
		System.out.println("RDI > rating.getScore: " + rating.getScore());
		System.out.println("RDI > rating.getComment: " + rating.getComment());

		try {
			template.update(sql, new Object[] { owneruuid, journouuid, score, comment });
			isSuccess = true;
		} catch (DataAccessException e) {
			isSuccess = false;
			e.printStackTrace();
		}
		
		System.out.println("RDI > isSuccess: " + isSuccess);

		return isSuccess;
	}

	@Override
	public Rating getRating(Rating rating) {
		
		UUID uuid = rating.getUuid();
		UUID owneruuid = rating.getOwneruuid();
		UUID journouuid = rating.getJournouuid();
		
		String sqlUuid = "SELECT * FROM ratings WHERE uuid = ?";
		String sqlJoinUuid = "SELECT * FROM ratings WHERE owneruuid = ? AND journouuid = ?";
		
		String sql = (uuid != null) ? sqlUuid : sqlJoinUuid;
		
//		if(uuid != null) {
//			sqlUuid += " uuid = " + uuid;
//		}
		
		Object[] fields = {
				(uuid != null ? uuid : owneruuid),
				(uuid != null ? null : journouuid)
		};
		
		List<Map<String, Object>> rows = null;
		
		try {
			rows = template.queryForList(sql, fields);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		for(Map<String, Object> row : rows) {
			rating.setUuid((UUID)row.get("uuid"));
			rating.setTimestamp((Timestamp)row.get("timestamp"));
			rating.setOwneruuid((UUID)row.get("owneruuid"));
			rating.setJournouuid((UUID)row.get("journouuid"));
			rating.setScore((int)row.get("score"));
			rating.setComment((String)row.get("comment"));
		}
		
		return rating;
	}

	@Override
	public boolean updateRating(Rating rating) {
		boolean isSuccess = false;

		String sql = "UPDATE ratings SET owneruuid = ?, journouuid = ?, score = ?, comment = ? WHERE uuid = ?";

		UUID uuid = rating.getUuid();
		UUID owneruuid = rating.getOwneruuid();
		UUID journouuid = rating.getJournouuid();
		int score = rating.getScore();
		String comment = rating.getComment();

		try {
			template.update(sql, new Object[] { owneruuid, journouuid, score, comment, uuid });
			isSuccess = true;
		} catch (DataAccessException e) {
			isSuccess = false;
			e.printStackTrace();
		}

		return isSuccess;
	}

	@Override
	public boolean deleteRating(Rating rating) {
		boolean isSuccess = false;

		String sql = "DELETE FROM ratings WHERE uuid = ?";

		UUID uuid = rating.getUuid();

		try {
			template.update(sql, new Object[] { uuid });
			isSuccess = true;
		} catch (DataAccessException e) {
			isSuccess = false;
			e.printStackTrace();
		}

		return isSuccess;
	}

}
