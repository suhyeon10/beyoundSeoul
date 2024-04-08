package com.youngsquad.record.presentation.response;

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
    private List<TravelNameResponse> travelNameResponseList;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TravelNameResponse {
        private String title;
        private long id;
    }

    public static RecordResponse from(Record record, List<Travel> travels) {
        return new RecordResponse(
                record.getImage(),
                record.getCreateDate(),
                record.getContent(),
                record.getMission().getTravelMissionSample().getMissionCategory().getName(),
                record.getMission().getTravelMissionSample().getTitle(),
                travels.stream().map(travel -> new TravelNameResponse(travel.getTitle(), travel.getId())).collect(Collectors.toList())
        );
    }
}
