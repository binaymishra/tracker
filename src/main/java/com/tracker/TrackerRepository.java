package com.tracker;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class TrackerRepository {
	
	private final String tableAinsert = "insert into table_a(userIp, userMac, apName, apMac, startTime, endTime) values(?, ?, ?, ?, ?, ?)";
	private final String tableBinsert = "insert into table_b(userIp, userMac, apName, apMac, startTime, endTime) values(?, ?, ?, ?, ?, ?)";
	private final String tableCinsert = "insert into table_c(prefix, lat, lon, name, category) values(?, ?, ?, ?, ?)";
	
	
	JdbcTemplate template;
	
	@Autowired
	public TrackerRepository(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
	}
	
	@Autowired
	TrackerComponent trackerComponent;
	
	public void insertTableA() throws IOException{
		List<TableA> tableA = trackerComponent.readFileTableA20120407();
		int[] rows = template.batchUpdate(tableAinsert, new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					TableA a = tableA.get(i);
					ps.setString(1, a.getUserIp());
					ps.setString(2, a.getUserMac());
					ps.setString(3, a.getApName());
					ps.setString(4, a.getApMac());
					ps.setLong(5, a.getStartTime());
					ps.setLong(6, a.getEndTime());
					
				}
				
				@Override
				public int getBatchSize() {
					return tableA.size();
				}
			});
		System.out.println(String.format("%d rows inserted in tableA ", rows.length));
	}
	
	public void insertTableB() throws IOException{
		List<TableB> tableB = trackerComponent.readFileTableB20120409();
			int[] rows = template.batchUpdate(tableBinsert, new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					TableB  b = tableB.get(i);
					ps.setString(1, b.getUserIp());
					ps.setString(2, b.getUserMac());
					ps.setString(3, b.getApName());
					ps.setString(4, b.getApMac());
					ps.setLong(5, b.getStartTime());
					ps.setLong(6, b.getEndTime());
					
				}
				
				@Override
				public int getBatchSize() {
					return tableB.size();
				}
			});
			System.out.println(String.format("%d rows inserted in tableB ", rows.length));
	}
	
	public void insertTableC() throws IOException{
		List<TableC> tableC = trackerComponent.readFileTableC();
		int[] rows = template.batchUpdate(tableCinsert, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				TableC c = tableC.get(i);
				ps.setString(1, c.getPrefix());
				ps.setDouble(2, c.getLat());
				ps.setDouble(3, c.getLon());
				ps.setString(4, c.getName());
				ps.setString(5, c.getCategory());
				
			}

			@Override
			public int getBatchSize() {
				return tableC.size();
			}
			
		});
		
		System.out.println(String.format("%d rows inserted in tableC ", rows.length));
	}

}
