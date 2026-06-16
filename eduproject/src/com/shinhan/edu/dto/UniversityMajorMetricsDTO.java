package com.shinhan.edu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter @Setter @ToString
public class UniversityMajorMetricsDTO {
    private int id;
    private int universityMajorFK;  // 학과 FK (UniversityMajorDTO의 id와 연결됨)
    private int reportyear;
    private double competitionrate;
    private int existingstudentcount;
    private int quotacount;
    private double employmentrate;
}
