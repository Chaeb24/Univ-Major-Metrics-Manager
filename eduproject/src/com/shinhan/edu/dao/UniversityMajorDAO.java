package com.shinhan.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shinhan.edu.dto.UniversityMajorDTO;
import com.shinhan.util.DBUtil;

public class UniversityMajorDAO {
	Connection conn;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	int resultCount;
	
	//삭제
	public int deleteUniversityMajor(int id) {
		String sql = "delete from universitymajor where id=?";
        conn = DBUtil.dbConnect();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            resultCount = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, ps, rs);
        }
		return resultCount;
	}
	
	//수정
	public int updateUniversityMajor(UniversityMajorDTO major) {
		String sql = "update universitymajor set universityidfk=?,"
				+ "name=?,region=?,daynight=? where id=?";
		conn = DBUtil.dbConnect();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, major.getUniversityIdFK());
			ps.setString(2, major.getName());
			ps.setString(3, major.getRegion());
			ps.setString(4, major.getDaynight());
			ps.setInt(5, major.getId());
			
			resultCount = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
	        DBUtil.dbDisconnect(conn, ps, rs);
	    }
		
		return resultCount;
	}
	
	
	//입력
	public int insertUniversityMajor(UniversityMajorDTO major) {
		String sql = "insert into universitymajor values(seq_major_id.nextval"
				+ ",?,?,?,?)";
		conn = DBUtil.dbConnect();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, major.getUniversityIdFK());
			ps.setString(2, major.getName());
			ps.setString(3, major.getRegion());
			ps.setString(4, major.getDaynight());
			resultCount = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbDisconnect(conn, ps, rs);
		}
		return resultCount;
	}
	// 대학 이름으로 소속 학과들을 '여러 건(List)' 가져오는 기능이므로 반환 타입을 List로 잡습니다.
	public List<UniversityMajorDTO> selectByUniversityName(String univName) {
	    List<UniversityMajorDTO> majorList = new ArrayList<>();
	    
	    String sql = "SELECT m.* "
	               + "FROM universitymajor m "
	               + "JOIN university u ON m.universityidFK = u.id "
	               + "WHERE u.name = ?";
	               
	    conn = DBUtil.dbConnect();
	    try {
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, univName); 
	        rs = ps.executeQuery();
	        
	        while(rs.next()) {
	            UniversityMajorDTO major = makeMajor(rs); 
	            majorList.add(major);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBUtil.dbDisconnect(conn, ps, rs);
	    }
	    return majorList;
	}
	
	public List<UniversityMajorDTO> selectAll(){
		List<UniversityMajorDTO> majorList = new ArrayList<>();
		String sql = "select * from universitymajor";
		conn = DBUtil.dbConnect();
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				UniversityMajorDTO major = makeMajor(rs);
				majorList.add(major);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return majorList;
	}

	private UniversityMajorDTO makeMajor(ResultSet rs) throws SQLException {
		UniversityMajorDTO major = new UniversityMajorDTO();
		
		major.setId(rs.getInt("id"));
		major.setUniversityIdFK(rs.getInt("universityidfk"));
		major.setName(rs.getString("name"));
		major.setRegion(rs.getString("region"));
		major.setDaynight(rs.getString("daynight"));
		return major;
	}
}
