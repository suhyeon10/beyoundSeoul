package com.youngsquad.travel.domain.service;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.youngsquad.mission.domain.Mission;
import com.youngsquad.mission.domain.MissionStatusType;
import com.youngsquad.travel.domain.model.Travel;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.youngsquad.mission.domain.QMission.mission;
import static com.youngsquad.mission.domain.QMissionStatus.missionStatus;

@Repository
public class HomeMissionStatusRepository {
    private final JPAQueryFactory queryFactory;

    public HomeMissionStatusRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    // 각 미션별 진행 완료 수 조회
    public List<Tuple> countMissionsByStatus(Travel travel) {
        return queryFactory
                .select(
                        mission.travelMissionSample.missionCategory,
                        missionStatus.missionStatusType,
                        mission.count()
                )
                .from(mission)
                .join(missionStatus).on(mission.eq(missionStatus.mission))
                .where(mission.travel.eq(travel)
                        .and(missionStatus.missionStatusType.eq(MissionStatusType.END)))
                .groupBy(mission.travelMissionSample.missionCategory, missionStatus.missionStatusType)
                .fetch();
    }

    // 진행 중인 미션들의 이름, id 반환
    public List<Mission> findOngoingMissionTitleAndId(Travel travel) {
        return queryFactory
                .select(mission)
                .from(mission)
                .join(missionStatus).on(mission.eq(missionStatus.mission))
                .where(mission.travel.eq(travel)
                        .and(missionStatus.missionStatusType.eq(MissionStatusType.ONGOING)))
                .fetch();
    }
}
