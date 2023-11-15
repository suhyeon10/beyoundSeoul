package com.youngsquad.reserve.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReserveRepo extends JpaRepository<Reserve, Long> {
//    List<Reserve> getReservesByArriveAndDepartDate(String arrive, LocalDate);
//    List<Reserve> getReservesByDepartAndDepartDate(String depart);
}
