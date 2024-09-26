package com.example.controller;

import java.util.List;
import java.util.Map;

import com.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Plan;
import com.example.service.PlannerService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/plan")
public class PlannerApiController {

    @Autowired
    private PlannerService plannerService;

    @Autowired
    HttpSession session;

    // 일정 추가
    @PostMapping("/insert")
    public ResponseEntity<Plan> insert(@RequestBody Plan plan) {
        try {
            User user = (User) session.getAttribute("loginUser");
            if (user == null) {
                throw new IllegalStateException("사용자 정보가 없습니다.");
            }
            plan.setUser(user);
            Plan savedPlan = plannerService.insert(plan);
            return new ResponseEntity<>(savedPlan, HttpStatus.CREATED);
        } catch (Exception e) {
            // 로그 기록 및 오류 메시지 반환
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 일정 목록 조회
    @GetMapping("/selectList")
    public List<Map<String, Object>> selectList() {
        User user = (User)session.getAttribute("loginUser");
        return plannerService.selectList(user);
    }

    @GetMapping("/{planId}")
    public Map<String, Object> selectPlan(@PathVariable(name = "planId") Long planId) {
        User user = (User)session.getAttribute("loginUser");
        return plannerService.selectPlan(planId, user);
    }

    // 일정 상세 조회
    @GetMapping("/selectDetail/{id}")
    public Plan selectDetail(@PathVariable(name = "id") Long id) {
        return plannerService.selectDetail(id).orElse(null);
    }

    // 일정 수정
    @PutMapping("/update")
    public Plan update(@RequestBody Plan plan) {
        return plannerService.update(plan);
    }

    // 일정 삭제
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        plannerService.delete(id);
    }
}

