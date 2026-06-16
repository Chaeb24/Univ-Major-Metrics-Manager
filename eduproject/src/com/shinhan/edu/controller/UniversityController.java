package com.shinhan.edu.controller;

import java.util.List;
import java.util.Scanner;

import com.shinhan.edu.dto.UniversityDTO;
import com.shinhan.edu.dto.UniversityMajorDTO;
import com.shinhan.edu.dto.UniversityMajorMetricsDTO;
import com.shinhan.edu.service.MajorService;
import com.shinhan.edu.service.UniversityMajorMetricsService;
import com.shinhan.edu.service.UniversityService;
import com.shinhan.edu.view.UniversityView;

public class UniversityController {
	static Scanner sc = new Scanner(System.in);
	static MajorService majorservice = new MajorService();
	static UniversityMajorMetricsService metricService = new UniversityMajorMetricsService();
	static UniversityService universityservice = new UniversityService();
	
	public static void main(String[] args) {
		boolean isRunning = true;
		while(isRunning) {
			UniversityView.menuDisplay();
			
			int menu = 0;
			try {
				menu = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("! 숫자로만 입력해 주세요.");
				continue;
			}
			
			switch(menu) {
				case 1 -> f_univSelectAll();
				case 2 -> f_univSelectByName();
				case 3 -> f_univInsert();
				case 4 -> f_univUpdate();
				case 5 -> f_univDelete();
				case 6 -> f_majorSelectAll();
				case 7 -> f_majorSelectByUnivName();
				case 8 -> f_majorInsert();
				case 9 -> f_majorUpdate();
				case 10 -> f_majorDelete();
				case 11 -> f_metricsSelectAll();
				case 12-> f_metricsSelectByKeyword();
				case 99 -> {
					System.out.println("\n프로그램을 종료합니다. 이용해 주셔서 감사합니다!");
					isRunning = false;
				}
				default -> System.out.println("! 메뉴에 없는 번호입니다. 다시 선택해 주세요.");
			}
		}
	}

	// 1. 대학 전체 조회
	private static void f_univSelectAll() {
		List<UniversityDTO> ulist = universityservice.selectAllService();
		UniversityView.printUniversityList(ulist);
	}

	// 2. 대학 이름으로 조회
	private static void f_univSelectByName() {
		System.out.print("조회할 대학명을 입력하세요 >> ");
		String name = sc.nextLine();
		UniversityDTO univ = universityservice.selectByNameService(name);
		UniversityView.printUniversity(univ);
	}

	// 3. 대학 신규 등록
	private static void f_univInsert() {
		System.out.println("\n[새로운 대학 정보 등록]");
		System.out.print("대학명 >> "); String name = sc.nextLine();
		System.out.print("주소 >> "); String address = sc.nextLine();
		System.out.print("연락처 >> "); String contact = sc.nextLine();
		System.out.print("홈페이지 URL >> "); String hp = sc.nextLine();
		System.out.print("입학처 URL >> "); String admin = sc.nextLine();
		
		UniversityDTO univ = UniversityDTO.builder().name(name).address(address).contact(contact)
				.homepage_url(hp).admission_url(admin).build();
		
		int result = universityservice.insertService(univ);
		UniversityView.printMessage("대학 등록", result);
	}

	// 4. 대학 정보 수정
	private static void f_univUpdate() {
		System.out.println("\n[대학 정보 수정]");
		System.out.print("수정할 대학의 고유 ID(번호) >> ");
		int id = Integer.parseInt(sc.nextLine());
		System.out.print("변경할 대학명 >> "); String name = sc.nextLine();
		System.out.print("변경할 주소 >> "); String address = sc.nextLine();
		System.out.print("변경할 연락처 >> "); String contact = sc.nextLine();
		System.out.print("변경할 홈페이지 URL >> "); String hp = sc.nextLine();
		System.out.print("변경할 입학처 URL >> "); String admin = sc.nextLine();
		
		UniversityDTO univ = UniversityDTO.builder()
				.id(id).name(name).address(address).contact(contact)
				.homepage_url(hp).admission_url(admin).build();
		
		int result = universityservice.updateService(univ);
		UniversityView.printMessage("대학 정보 수정", result);
	}

	// 5. 대학 삭제
	private static void f_univDelete() {
		System.out.print("\n삭제할 대학의 고유 ID(번호)를 입력하세요 >> ");
		int id = Integer.parseInt(sc.nextLine());
		int result = universityservice.deleteService(id);
		UniversityView.printMessage("대학 정보 삭제", result);
	}

	// 6. 학과 전체 조회
	private static void f_majorSelectAll() {
		List<UniversityMajorDTO> mlist = majorservice.selectAllService();
		UniversityView.printMajorList(mlist);
	}

	// 7. 대학명으로 소속 학과 조회
	private static void f_majorSelectByUnivName() {
		System.out.print("학과 목록을 볼 소속 대학 이름을 입력하세요 >> ");
		String univName = sc.nextLine();
		List<UniversityMajorDTO> mlist = majorservice.selectByUniversityNameService(univName);
		UniversityView.printMajorList(mlist);
	}

	// 8. 학과 등록 (외래키 universityIdFK를 받도록 수정)
	private static void f_majorInsert() {
		System.out.println("\n [새로운 학과 정보 등록]");
		System.out.print("소속할 대학의 고유 ID(번호) >> "); int univId = Integer.parseInt(sc.nextLine());
		System.out.print("학과명 >> "); String name = sc.nextLine();
		System.out.print("지역 >> "); String region = sc.nextLine();
		System.out.print("주간 or 야간 >> "); String daynight = sc.nextLine();
		
		UniversityMajorDTO major = UniversityMajorDTO.builder()
				.universityIdFK(univId) // 대학 외래키 필수 매핑
				.name(name).region(region).daynight(daynight).build();
				
		int result = majorservice.insertUniversityMajorService(major);
		UniversityView.printMessage("학과 등록", result);
	}

	// 9. 학과 수정 (학과 ID 매핑 추가)
	private static void f_majorUpdate() {
		System.out.println("\n [학과 정보 수정]");
		System.out.print("수정할 학과의 고유 ID(번호) >> "); int id = Integer.parseInt(sc.nextLine());
		System.out.print("소속 대학 ID(번호) 수정 >> "); int univId = Integer.parseInt(sc.nextLine());
		System.out.print("변경할 학과명 >> "); String name = sc.nextLine();
		System.out.print("변경할 지역 >> "); String region = sc.nextLine();
		System.out.print("변경할 주간 or 야간 >> "); String daynight = sc.nextLine();
		
		UniversityMajorDTO major = UniversityMajorDTO.builder()
				.id(id) // 수정 타겟 PK 세팅
				.universityIdFK(univId)
				.name(name).region(region).daynight(daynight).build();
		
		int result = majorservice.updateUniversityMajorService(major);
		UniversityView.printMessage("학과 정보 수정", result);
	}

	// 10. 학과 삭제
	private static void f_majorDelete() {
		System.out.print("\n삭제할 학과의 고유 ID(번호)를 입력하세요 >> ");
		int id = Integer.parseInt(sc.nextLine());
		int result = majorservice.deleteUniversityMajorService(id);
		UniversityView.printMessage("학과 정보 삭제", result);
	}

	// 11. 지표 전체 조회
	private static void f_metricsSelectAll() {
		List<UniversityMajorMetricsDTO> mlist = metricService.selectAllService();
		UniversityView.printMetricsList(mlist);
	}

	// 12. 대통합 키워드 검색 지표 조회
	private static void f_metricsSelectByKeyword() {
		System.out.print("검색할 대학 이름을 입력하세요 >> ");
		String univName = sc.nextLine();
		System.out.print("검색할 대학 학과를 입력하세요 >> ");
		String major = sc.nextLine();
		List<UniversityMajorMetricsDTO> mtlist = metricService.selectByKeywordService(univName,major);
		UniversityView.printMetricsList(mtlist);
	}
}