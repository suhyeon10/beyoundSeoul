package com.youngsquad.reserve;

import com.youngsquad.reserve.dto.BusReq;
import com.youngsquad.reserve.dto.BusReserveRes;
import com.youngsquad.reserve.dto.ReserveModalRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReserveService {

    // 셔틀버스 예약 시간표 조회
    public ReserveModalRes getModal(BusReq busReq){
        long uid = busReq.getUid();
//        busReq.getAirport()

        return ReserveModalRes.builder()
                .depart(ReserveModalRes.Depart.builder()
                        .destination("부산 / BUSAN")
                        .date("")
                        .reserveNum(3)
                        .schedules(Collections.singletonList(ReserveModalRes.Depart.Schedule.builder()
                                .time("10시 30분 출발")
                                .seat("17/30")
                                .build()))
                        .build())

                .arrive(ReserveModalRes.Arrive.builder()
                        .destination("인천공항 / BUSAN")
                        .date("")
                        .reserveNum(3)
                        .schedules(Collections.singletonList(ReserveModalRes.Arrive.Schedule.builder()
                                .time("10시 30분 출발")
                                .seat("17/30")
                                .build()))
                        .build())
                .build();

    }

    // 버스 예약 내역 조회
    public BusReserveRes getBusReserveModal(int uid){
        return BusReserveRes.builder()
                .busReserves(
                        Collections.singletonList(BusReserveRes.BusReserve.builder()
                                .depart("동서울")
                                .arrive("영주")
                                .departDate("2023.12.18")
                                .arriveDate("2023.12.18")
                                .seat("24")
                                .build())
                )
                .build();
    }

}
