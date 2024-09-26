package com.example.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SNS_INFO")
public class SnsInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sns_info_id_seq")
    @SequenceGenerator(name = "sns_info_id_seq", sequenceName = "sns_info_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "sns_id")
    private String snsId;

    @Column(name = "sns_type")
    private String snsType;

    @Column(name = "sns_name")
    private String snsName;

    @Column(name = "sns_profile")
    private String snsProfile;

    @Column(name = "sns_connect_date")
    private Date snsConnectDate;

    @Column(name = "provider", nullable = false)
    private String provider;

    @Column(name = "provider_user_id", nullable = false)
    private String providerUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;
}
