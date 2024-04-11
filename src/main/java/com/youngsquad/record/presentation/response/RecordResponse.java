package com.youngsquad.record.presentation.response;

import com.youngsquad.record.application.RecordReadService;
import com.youngsquad.record.domain.Record;
import com.youngsquad.travel.domain.model.Travel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordResponse {

    private String image;
    private LocalDateTime uploadAt;
    private String comment;
    private String missionType;
    private String missionTitle;



    public static RecordResponse fromRecordList(Record record, RecordReadService recordReadService) {
        return new RecordResponse(
                recordReadService.getRecordImageURL(record.getImage()),
                record.getCreateDate(),
                record.getContent(),
                record.getMission().getTravelMissionSample().getMissionCategory().getName(),
                record.getMission().getTravelMissionSample().getTitle()
        );
    }




}
