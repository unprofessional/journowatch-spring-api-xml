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
		boolean isSuccess = false;

		String sql = "INSERT INTO ratings (timestamp, owneruuid, journouuid, score, comment) VALUES (?, ?, ?, ?, ?) ";

		Timestamp timestamp = rating.getTimestamp();
		UUID owneruuid = rating.getOwner();
		UUID journouuid = rating.getJourno();
		int score = rating.getScore();
		String comment = rating.getComment();

		try {
			template.update(sql, new Object[] { timestamp, owneruuid, journouuid, score, comment });
			isSuccess = true;
		} catch (DataAccessException e) {
			isSuccess = false;
			e.printStackTrace();
		}

		return isSuccess;
	}

	@Override
	public Rating getRating(Rating rating) {
		
		String sql = "SELECT * FROM ratings WHERE uuid = ?";
		
		List<Map<String, Object>> rows = null;
		
		try {
			rows = template.queryForList(sql, new Object[]{}, rse);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		for(Map<String, Object> row : rows) {
			rating.setUuid((UUID)row.get("uuid"));
			rating.setTimestamp((Timestamp)row.get("timestamp"));
		}
		
		return rating;
	}

	@Override
	public boolean updateRating(Rating rating) {
		boolean isSuccess = false;

		String sql = "UPDATE ratings SET timestamp = ?, ownerduuid = ?, journouuid = ?, score = ?, comment = ?";

		Timestamp timestamp = rating.getTimestamp();
		UUID owneruuid = rating.getOwner();
		UUID journouuid = rating.getJourno();
		int score = rating.getScore();
		String comment = rating.getComment();

		try {
			template.update(sql, new Object[] { timestamp, owneruuid, journouuid, score, comment });
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
