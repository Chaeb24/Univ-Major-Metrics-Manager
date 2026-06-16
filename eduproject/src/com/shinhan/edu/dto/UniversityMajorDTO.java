package com.shinhan.edu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter@ToString
@Builder
public class UniversityMajorDTO {
	private int id;
	private int universityIdFK;
	private String name;
	private String region;
	private String daynight;
}
