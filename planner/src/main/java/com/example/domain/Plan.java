package com.example.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.example.util.BooleanToNumberConverter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "PLAN")
public class Plan {
    @Id
    @SequenceGenerator (
            name = "myPlannerSEQ",
            sequenceName = "Planner_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(generator = "myPlannerSEQ")
    private Long planId; // 할 일 번호

    @NonNull
    private String title; // 제목

    @Convert(converter = BooleanToNumberConverter.class)
    private boolean allDay; // 하루종일 여부

    @Convert(converter = BooleanToNumberConverter.class)
    private boolean alarm; // 알람 여부

    @NonNull
//  @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDate startDate; // 시작일

    private LocalDate endDate; // 종료일
    // String으로 하면 날짜 변경을 못한다

    private LocalTime startTime; // 시작 시간

    private LocalTime endTime; // 종료 시간

    private String repeat; // 반복 여부

    private String content; // 내용
    private String place; // 위치

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

}