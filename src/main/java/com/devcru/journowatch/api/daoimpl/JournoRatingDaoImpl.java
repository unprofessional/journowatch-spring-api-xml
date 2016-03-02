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

import com.devcru.journowatch.api.dao.JournoRatingDao;
import com.devcru.journowatch.api.objects.JournoRating;

public class JournoRatingDaoImpl implements JournoRatingDao {

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
	public boolean createRating(JournoRating journorating) {
		
		System.out.println("JRDI > reached!");
		
		boolean isSuccess = false;

		String sql = "INSERT INTO journoratings (status, owneruuid, journouuid, score, headline, comment) VALUES (?, ?, ?, ?, ?, ?)";

		int status = journorating.getStatus();
		UUID owneruuid = journorating.getOwneruuid();
		UUID journouuid = journorating.getJournouuid();
		int score = journorating.getScore();
		String headline = journorating.getHeadline();
		String comment = journorating.getComment();
		
		System.out.println("JRDI > journorating.getUuid: " + journorating.getUuid());
		System.out.println("JRDI > journorating.getTimestamp: " + journorating.getTimestamp());
		System.out.println("JRDI > journorating.getStatus: " + journorating.getStatus());
		System.out.println("JRDI > journorating.getOwneruuid: " + journorating.getOwneruuid());
		System.out.println("JRDI > journorating.getJournouuid: " + journorating.getJournouuid());
		System.out.println("JRDI > journorating.getScore: " + journorating.getScore());
		System.out.println("JRDI > journorating.getHeadline: " + journorating.getHeadline());
		System.out.println("JRDI > journorating.getComment: " + journorating.getComment());

		try {
			template.update(sql, new Object[] { status, owneruuid, journouuid, score, headline, comment });
			isSuccess = true;
		} catch (DataAccessException e) {
			isSuccess = false;
			e.printStackTrace();
		}
		
		System.out.println("JRDI > isSuccess: " + isSuccess);

		return isSuccess;
	}

	@Override
	public JournoRating getRating(JournoRating journorating) {
		
		UUID uuid = journorating.getUuid();
		UUID ouuid = journorating.getOwneruuid();
		UUID juuid = journorating.getJournouuid();
		
		String sqlUuid = "SELECT * FROM journoratings WHERE uuid = ?";
		String sqlJoinUuid = "SELECT * FROM journoratings WHERE owneruuid = ? AND journouuid = ?";
		
		List<Map<String, Object>> rows = null;
		
		try {
			rows = template.queryForList(
				(uuid != null ? sqlUuid : sqlJoinUuid),
				(uuid != null ? new Object[]{uuid} : new Object[]{ouuid, juuid})
			);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		for(Map<String, Object> row : rows) {
			journorating.setUuid((UUID)row.get("uuid"));
			journorating.setTimestamp((Timestamp)row.get("timestamp"));
			journorating.setStatus((int)row.get("status"));
			journorating.setOwneruuid((UUID)row.get("owneruuid"));
			journorating.setJournouuid((UUID)row.get("journouuid"));
			journorating.setScore((int)row.get("score"));
			journorating.setHeadline((String)row.get("headline"));
			journorating.setComment((String)row.get("comment"));
		}
		
		return journorating;
	}

	@Override
	public boolean updateRating(JournoRating journorating) {
		boolean isSuccess = false;

		String sql = "UPDATE journoratings SET status = ?, owneruuid = ?, journouuid = ?, "
				+ "score = ?, headline = ?, comment = ? WHERE uuid = ?";

		int status = journorating.getStatus();
		UUID uuid = journorating.getUuid();
		UUID owneruuid = journorating.getOwneruuid();
		UUID journouuid = journorating.getJournouuid();
		int score = journorating.getScore();
		String headline = journorating.getHeadline();
		String comment = journorating.getComment();

		try {
			template.update(sql, new Object[] { status, owneruuid, journouuid, score, headline, comment, uuid });
			isSuccess = true;
		} catch (DataAccessException e) {
			isSuccess = false;
			e.printStackTrace();
		}

		return isSuccess;
	}

	@Override
	public boolean deleteRating(JournoRating journorating) {
		boolean isSuccess = false;

		String sql = "DELETE FROM journoratings WHERE uuid = ?";

		UUID uuid = journorating.getUuid();

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
