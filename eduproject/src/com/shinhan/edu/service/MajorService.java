package com.shinhan.edu.service;

import java.util.List;

import com.shinhan.edu.dao.UniversityMajorDAO;
import com.shinhan.edu.dto.UniversityMajorDTO;

public class MajorService {
	UniversityMajorDAO universitymajordao = new UniversityMajorDAO();
	
	public int deleteUniversityMajorService(int id) {
		int result = universitymajordao.deleteUniversityMajor(id);
		return result;
	}
	
	public int updateUniversityMajorService(UniversityMajorDTO major) {
		int result = universitymajordao.updateUniversityMajor(major);
		return result;
	}
	
	public int insertUniversityMajorService(UniversityMajorDTO major) {
		int result = universitymajordao.insertUniversityMajor(major);
		return result;
	}
	
	public  List<UniversityMajorDTO> selectByUniversityNameService(String name){
		return universitymajordao.selectByUniversityName(name);
	}
	
	public List<UniversityMajorDTO> selectAllService(){
		return universitymajordao.selectAll();
	}
}
