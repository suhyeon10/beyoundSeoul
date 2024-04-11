package com.youngsquad.record.presentation.response;

import com.youngsquad.travel.domain.model.Travel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelNameResponse {
    private String title;
    private long id;

    public static List<TravelNameResponse> fromTravelNames(List<Travel> travels){
        return travels.stream()
                .map(travel -> new TravelNameResponse(travel.getTitle(), travel.getId()))
                .collect(Collectors.toList());
    }
}
