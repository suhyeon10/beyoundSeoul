package com.youngsquad.record.domain.service;

import com.youngsquad.record.domain.Record;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface RecordRepository extends JpaRepository<Record, Long> {

    @Query("SELECT rc FROM Record rc " +
            "LEFT JOIN rc.mission m " +
            "WHERE rc.id < :cursor AND m.travel.id = :travelId " + // 여기에 공백 추가
            "ORDER BY rc.id DESC")
    Slice<Record> findRecordsByTravelId(@Param("travelId") Long travelId,
                                        @Param("cursor") Long cursor,
                                        Pageable pageable);

}
