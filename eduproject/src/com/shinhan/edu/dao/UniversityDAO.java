package com.shinhan.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shinhan.edu.dto.UniversityDTO;
import com.shinhan.util.DBUtil;



public class UniversityDAO {
	//DB연결
	Connection conn;
	// SQL문 보내기 위한 통로
	Statement st;
	// SQL문 보내기 위한 통로, ?가능
	PreparedStatement ps;
	// Select 결과를 받음.
	ResultSet rs;
	// DML 결과, 영향을 받은 건수
	int resultCount;
	
	//삭제
	public int deleteUniversity(int id) {
		String sql = "delete from university where id=?";
		conn = DBUtil.dbConnect();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			resultCount = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbDisconnect(conn, ps, rs);
		}
		
		return resultCount;
	}
	
	//수정
	public int updateUniversity(UniversityDTO university) {
	    // SQL문 변경: id를 기준으로 찾아서 나머지 정보들을 업데이트합니다.
	    String sql = "update university set "
	            + "name=?, address=?, contact=?, homepage_url=?, admission_url=? "
	            + "where id=?";
	            
	    conn = DBUtil.dbConnect();
	    try {
	        ps = conn.prepareStatement(sql);
	        
	        // SQL문 물음표(?) 순서에 정확하게 매핑합니다.
	        ps.setString(1, university.getName());          // 1번째 ? (name)
	        ps.setString(2, university.getAddress());       // 2번째 ? (address)
	        ps.setString(3, university.getContact());       // 3번째 ? (contact)
	        ps.setString(4, university.getHomepage_url());  // 4번째 ? (homepage_url)
	        ps.setString(5, university.getAdmission_url()); // 5번째 ? (admission_url)
	        
	        // 데이터를 찾아낼 기준인 Primary Key
	        ps.setInt(6, university.getId());               // 6번째 ? (where id)
	        
	        resultCount = ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBUtil.dbDisconnect(conn, ps, rs);
	    }
	    
	    return resultCount;
	}
	
	
	
	//입력
	public int insertUniversity(UniversityDTO university) {
		String sql = "insert into university values(seq_univ_id.nextval,?,?,?,?,?)";
		conn = DBUtil.dbConnect();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, university.getName());
			ps.setString(2, university.getAddress());
			ps.setString(3, university.getContact());
			ps.setString(4, university.getHomepage_url());
			ps.setString(5, university.getAdmission_url());
			resultCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbDisconnect(conn, ps, rs);
		}
		return resultCount;
	}
	
	
	public UniversityDTO selectByName(String name) {
		UniversityDTO university = null;
		String sql = "select * from university where name like ?";
		
	
		conn = DBUtil.dbConnect();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,"%"+ name.trim() +"%");
			rs = ps.executeQuery();
			if(rs.next()) {
			    university = makeUniversity(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		
		return university;
	}
	
	public List<UniversityDTO> selectAll(){
		List<UniversityDTO> univList = new ArrayList<>();
		String sql = "select * from university";
		conn = DBUtil.dbConnect();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				UniversityDTO university = makeUniversity(rs);
				univList.add(university);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return univList;
	}

	private UniversityDTO makeUniversity(ResultSet rs) throws SQLException {
		UniversityDTO university = new UniversityDTO();
		university.setId(rs.getInt("id"));
		university.setName(rs.getString("name"));
		university.setAddress(rs.getString("address"));
		university.setContact(rs.getString("contact"));
		university.setHomepage_url(rs.getString("homepage_url"));
		university.setAdmission_url(rs.getString("admission_url"));
		return university;
	}
	
}
