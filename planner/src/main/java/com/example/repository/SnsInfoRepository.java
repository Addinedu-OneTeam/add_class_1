package com.example.repository;

import com.example.domain.SnsInfo;
import com.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SnsInfoRepository extends JpaRepository<SnsInfo, Long> {

//    void deleteAllByUser(User user);
}
