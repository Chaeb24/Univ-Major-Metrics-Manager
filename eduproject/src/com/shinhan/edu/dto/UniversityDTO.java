package com.shinhan.edu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
@Builder
public class UniversityDTO {
	private int id;
	private String name;
	private String address;
	private String contact;
	private String homepage_url;
	private String admission_url;
}
