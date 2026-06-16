package com.shinhan.edu.service;

import java.util.List;

import com.shinhan.edu.dao.UniversityMajorMetricsDAO;
import com.shinhan.edu.dto.UniversityMajorMetricsDTO;



public class UniversityMajorMetricsService {
	UniversityMajorMetricsDAO metric = new UniversityMajorMetricsDAO();
	
	public int insertMetricsService(UniversityMajorMetricsDTO metrics) {
		int result = metric.insertMetrics(metrics);
		return result;
	}
	
	public List<UniversityMajorMetricsDTO> selectByKeywordService(String univName,String majorName){
		return metric.selectByKeyword(univName,majorName);
		
	}
	
	public List<UniversityMajorMetricsDTO> selectAllService(){
		return metric.selectAll();
	}
}
