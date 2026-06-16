package com.shinhan.edu.service;

import java.util.List;

import com.shinhan.edu.dao.UniversityDAO;
import com.shinhan.edu.dto.UniversityDTO;

public class UniversityService {
	UniversityDAO universityDAO = new UniversityDAO();
	
	public int deleteService(int id) {
		int result = universityDAO.deleteUniversity(id);
		return result;
	}
	
	public int updateService(UniversityDTO univ) {
		int result = universityDAO.updateUniversity(univ);
		return result;
	}
	
	public int insertService(UniversityDTO univ) {
		int result = universityDAO.insertUniversity(univ);
		return result;
	}
	
	public UniversityDTO selectByNameService(String name) {
		UniversityDTO univ = universityDAO.selectByName(name);
		return univ;
	}
	
	public List<UniversityDTO> selectAllService(){
		return universityDAO.selectAll();	
	}
}
