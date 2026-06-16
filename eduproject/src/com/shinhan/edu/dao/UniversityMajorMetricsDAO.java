package com.shinhan.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shinhan.edu.dto.UniversityMajorMetricsDTO;
import com.shinhan.util.DBUtil;

public class UniversityMajorMetricsDAO {
	Connection conn;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	int resultCount;

	
	//입력
	public int insertMetrics(UniversityMajorMetricsDTO metrics) {
		String sql = "insert into universitymajor_metrics values(seq_metric_id.nextval, ?, ?, ?, ?, ?, ?)";
		
		conn = DBUtil.dbConnect();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, metrics.getUniversityMajorFK());
			ps.setInt(2, metrics.getReportyear());
			ps.setDouble(3, metrics.getCompetitionrate());
			ps.setInt(4, metrics.getExistingstudentcount());
			ps.setInt(5, metrics.getQuotacount());
			ps.setDouble(6,metrics.getEmploymentrate());
			
			resultCount = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbDisconnect(conn, ps, rs);
		}
		
		return resultCount;
	}
	
	//대학이름 또는 학과로 조회
	public List<UniversityMajorMetricsDTO> selectByKeyword(String univName,String majorName){
		List<UniversityMajorMetricsDTO> metricsList = new ArrayList<>();
		String sql = "select mt.*, u.name as univ_name, m.name as major_name "
				+ " from universitymajor_metrics mt "
				+ " join universitymajor m on mt.universityMajorFK = m.id "
				+ " join university u on m.universityIdFK = u.id "
				+ " where u.name = ? and m.name = ? "
				+ " ORDER BY mt.reportyear DESC";
		
		conn = DBUtil.dbConnect();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, univName);
			ps.setString(2, majorName);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				UniversityMajorMetricsDTO metrics = makeMetrics(rs);
				metricsList.add(metrics);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbDisconnect(conn, ps, rs);
		}
		
		return metricsList;
	}
	
	//조회
	public List<UniversityMajorMetricsDTO> selectAll(){
		List<UniversityMajorMetricsDTO> metricsList = new ArrayList<>();
		String sql = "select * from universitymajor_metrics";
		conn = DBUtil.dbConnect();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				UniversityMajorMetricsDTO metrics = makeMetrics(rs);
				metricsList.add(metrics);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return metricsList;
	}

	
	private UniversityMajorMetricsDTO makeMetrics(ResultSet rs) throws SQLException {
		UniversityMajorMetricsDTO metrics = new UniversityMajorMetricsDTO();
		metrics.setId(rs.getInt("id"));
		metrics.setUniversityMajorFK(rs.getInt("universityMajorFK"));
		metrics.setReportyear(rs.getInt("reportyear"));
		metrics.setCompetitionrate(rs.getDouble("competitionrate"));       
		metrics.setExistingstudentcount(rs.getInt("existingstudentcount"));
		metrics.setQuotacount(rs.getInt("quotacount"));
		metrics.setEmploymentrate(rs.getDouble("employmentrate"));         
		return metrics;
	}
}
