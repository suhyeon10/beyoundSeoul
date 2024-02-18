package com.youngsquad.travel.domain.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.youngsquad.travel.domain.model.Travel;
import org.springframework.stereotype.Repository;

import static com.youngsquad.travel.domain.model.QTravel.travel;
import static com.youngsquad.travel.domain.model.QTravelParticipate.travelParticipate;

@Repository
public class HomeProfileRepositoryImpl implements HomeProfileRepository{
    private final JPAQueryFactory jpaQueryFactory;

    public HomeProfileRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Travel findLatestTravelByUserId(Long userId) {
        return jpaQueryFactory
                .selectFrom(travel)
                .innerJoin(travelParticipate).on(travel.id.eq(travelParticipate.travel.id))
                .where(travelParticipate.teamMember.id.eq(userId))
                .orderBy(travel.createAt.desc())
                .fetchFirst();
    }

}
