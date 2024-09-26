package com.example.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_LOG")
public class UserLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_log_id_seq")
    @SequenceGenerator(name = "user_log_id_seq", sequenceName = "user_log_id_seq", allocationSize = 1)
    private Long logId;

    @Column(name = "login_date", nullable = false)
    private LocalDateTime loginDate = LocalDateTime.now();

    @Column(name = "login_type", nullable = false)
    private String loginType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}