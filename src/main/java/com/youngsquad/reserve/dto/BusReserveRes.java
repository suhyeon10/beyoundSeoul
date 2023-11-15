package com.youngsquad.reserve.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class BusReserveRes {

    private List<BusReserve> busReserves;

    @Getter
    @Builder
    public static class BusReserve{
        private String depart;
        private String arrive;
        private String departDate;
        private String arriveDate;
        private String departTime;
        private String seat;
    }

}
