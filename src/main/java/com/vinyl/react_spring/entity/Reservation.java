package com.vinyl.react_spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String eventName;           // 행사명

    @Column(nullable = false)
    private String representativeName;  // 대표자명

    @Column(nullable = false)
    private String phone;               // 전화번호

    @Column(nullable = false)
    private LocalTime rehearsalStartTime;   // 리허설 시작시간

    @Column(nullable = false)
    private LocalTime performanceStartTime; // 공연 시작시간

    @Column(nullable = false)
    private String status;  // 대기중, 승인, 거절

    @Column(nullable = false)
    private LocalDateTime createdAt;    // 신청일시

    public Reservation(String eventName, String representativeName, String phone,
                       LocalTime rehearsalStartTime, LocalTime performanceStartTime) {
        this.eventName = eventName;
        this.representativeName = representativeName;
        this.phone = phone;
        this.rehearsalStartTime = rehearsalStartTime;
        this.performanceStartTime = performanceStartTime;
        this.status = "대기중";
        this.createdAt = LocalDateTime.now();
    }

    public void approve() {
        this.status = "승인";
    }

    public void reject() {
        this.status = "거절";
    }
}