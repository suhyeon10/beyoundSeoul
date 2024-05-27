package com.youngsquad.travel.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTravelParticipate is a Querydsl query type for TravelParticipate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTravelParticipate extends EntityPathBase<TravelParticipate> {

    private static final long serialVersionUID = 2119728106L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTravelParticipate travelParticipate = new QTravelParticipate("travelParticipate");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.youngsquad.user.domain.model.QUser teamMember;

    public final EnumPath<TeamMemberRole> teamMemberRole = createEnum("teamMemberRole", TeamMemberRole.class);

    public final QTravel travel;

    public QTravelParticipate(String variable) {
        this(TravelParticipate.class, forVariable(variable), INITS);
    }

    public QTravelParticipate(Path<? extends TravelParticipate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTravelParticipate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTravelParticipate(PathMetadata metadata, PathInits inits) {
        this(TravelParticipate.class, metadata, inits);
    }

    public QTravelParticipate(Class<? extends TravelParticipate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.teamMember = inits.isInitialized("teamMember") ? new com.youngsquad.user.domain.model.QUser(forProperty("teamMember")) : null;
        this.travel = inits.isInitialized("travel") ? new QTravel(forProperty("travel")) : null;
    }

}

