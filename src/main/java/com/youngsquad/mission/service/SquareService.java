package com.youngsquad.mission.service;

import com.youngsquad.mission.dto.MissionSquareRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SquareService {

    public MissionSquareRes getSquareData(int uid){
        return MissionSquareRes.builder()
                .destination("Busan")
                .missions(
                        Collections.singletonList(MissionSquareRes.Mission.builder()
                                .readerThumb("")
                                .sex("여")
                                .age("19세")
                                .comment("어쩌라구여")
                                .totalNum(10)
                                .memImages(List.of("", "", ""))
                                .build())
                )
                .build();
    }
}
