package com.youngsquad.home.travel;

import com.youngsquad.mission.domain.Mission;
import com.youngsquad.travel.domain.model.Travel;
import com.youngsquad.travel.domain.model.mission.MissionCategory;
import com.youngsquad.travel.domain.model.mission.TravelMissionSample;
import com.youngsquad.travel.domain.service.mission.MissionRepository;
import com.youngsquad.travel.domain.service.mission.TravelMissionSampleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@DataJpaTest
public class OnboardMissionTest {
    @Autowired
    private MissionRepository missionRepository;
    @Autowired
    private TravelMissionSampleRepository travelMissionSampleRepository;


    @Test
    public void createMission() {

        Travel travel = Travel.builder().build();
        String destination = "부산";
        List<Mission> missions = new ArrayList<>();

        addRandomMissionSample(travel, missions, MissionCategory.TOUR, destination);
        addRandomMissionSample(travel, missions, MissionCategory.SOSO, destination);
        addRandomMissionSample(travel, missions, MissionCategory.FOOD, destination);

        missionRepository.saveAll(missions);
    }

    private void addRandomMissionSample(Travel travel, List<Mission> missions, MissionCategory missionCategory, String destination) {
        List<TravelMissionSample> missionSamples = travelMissionSampleRepository.findByTravelMissionDestinationAndMissionCategory(destination, missionCategory);
        if (!missionSamples.isEmpty()) {
            Collections.shuffle(missionSamples);
            missions.add(Mission.makeEntity(travel, getRandomElement(missionSamples)));
        }
    }

    private <T> T getRandomElement(List<T> list) {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }
}
