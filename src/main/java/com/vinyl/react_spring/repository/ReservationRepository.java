package com.vinyl.react_spring.repository;

import com.vinyl.react_spring.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByOrderByCreatedAtDesc(); // 최신순 조회
}