package com.example.repository;

import java.util.List;
import java.util.Optional;

import com.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.domain.Plan;

@Repository
public interface PlannerRepository extends JpaRepository<Plan, Long> {

    List<Plan> findAllByUser(User user);

    Optional<Plan> findByplanIdAndUser(Long planId, User user);
}

