package com.shinhan.edu.view;

import java.util.List;

import com.shinhan.edu.dto.UniversityDTO;
import com.shinhan.edu.dto.UniversityMajorDTO;
import com.shinhan.edu.dto.UniversityMajorMetricsDTO;

public class UniversityView {
	
	// 메뉴판 디스플레이
	public static void menuDisplay() {
		System.out.println("\n================= [대학 정보 통합 관리 시스템] =================");
		System.out.println(" [대학 관리]  1.대학전체조회  2.대학이름조회  3.대학등록  4.대학수정  5.대학삭제");
		System.out.println(" [학과 관리]  6.학과전체조회  7.대학별학과조회 8.학과등록 9.학과수정 10.학과삭제");
		System.out.println(" [지표 관리]  11.지표전체조회 12.통합검색지표조회 (대학명/학과명 검색)");
		System.out.println(" [기타]       99.프로그램 종료");
		System.out.println("=================================================================");
		System.out.print("원하는 작업 번호를 입력하세요 >> ");
	}
	
	// 성공/실패 메시지 통합 처리
	public static void printMessage(String m, int resultCount) {
		if(resultCount > 0) {
			System.out.println("[성공] 요청하신 " + m + " 작업이 정상 처리되었습니다. (" + resultCount + "건)");
		} else {
			System.out.println("[실패] 조건에 맞는 데이터가 없거나 작업이 실패했습니다.");
		}
	}

	// 대학 전체 목록 출력
	public static void printUniversityList(List<UniversityDTO> ulist) {
		System.out.println("\n[대학 전체 목록]");
		if(ulist == null || ulist.isEmpty()) {
			System.out.println("등록된 대학 데이터가 없습니다.");
			return;
		}
		ulist.forEach(u -> System.out.println("대학명: " + u.getName() + " | 주소: " + u.getAddress()));
	}
		
	// 대학 단건 상세 출력
	public static void printUniversity(UniversityDTO univ) {
		System.out.println("\n [대학 상세 정보]");
		if(univ == null) {
			System.out.println("해당 대학이 존재하지 않습니다.");
			return;
		}
		System.out.println("대학명: " + univ.getName());
		System.out.println("주소: " + univ.getAddress() + " | 연락처: " + univ.getContact());
		System.out.println("홈페이지: " + univ.getHomepage_url());
		System.out.println("입학처홈피: " + univ.getAdmission_url());
	}

	// 학과 목록 출력
	public static void printMajorList(List<UniversityMajorDTO> mlist) {
		System.out.println("\n [학과 목록 조회 결과]");
		if(mlist == null || mlist.isEmpty()) {
			System.out.println("등록된 학과 데이터가 없습니다.");
			return;
		}
		mlist.forEach(m -> System.out.println("학과명: " + m.getName() + " | 지역: " + m.getRegion() + " | 구분: " + m.getDaynight()));
	}

	
	public static void printMetricsList(List<UniversityMajorMetricsDTO> mlist) {
	    System.out.println("\n [학과 지표 목록]");
	    if(mlist == null || mlist.isEmpty()) {
	        System.out.println("등록된 지표 데이터가 없습니다.");
	        return;
	    }
	    
	    // 고정 폭을 사용하여 깔끔하게 서식을 지정합니다. (좌측 정렬을 위해 '-' 붙임)
	    String format = "%-10s\t%-8s\t%-8s\t%-8s\t%-8s\n";
	    String dataFormat = "%d년\t\t%-10.2f\t%-7d명\t%-7d명\t%-7.1f%%\n";
	    
	    System.out.println("---------------------------------------------------------------------------------");
	    System.out.printf(format, "공시연도", "경쟁률", "재학생 수", "입학 정원", "취업률");
	    System.out.println("---------------------------------------------------------------------------------");
	    
	    for(UniversityMajorMetricsDTO mt : mlist) {
	        System.out.printf(dataFormat,
	                mt.getReportyear(),
	                mt.getCompetitionrate(), 
	                mt.getExistingstudentcount(),
	                mt.getQuotacount(), 
	                mt.getEmploymentrate());
	    }
	    System.out.println("---------------------------------------------------------------------------------");
	}
}