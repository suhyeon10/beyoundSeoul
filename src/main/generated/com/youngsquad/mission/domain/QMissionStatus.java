package com.youngsquad.mission.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMissionStatus is a Querydsl query type for MissionStatus
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMissionStatus extends EntityPathBase<MissionStatus> {

    private static final long serialVersionUID = -273527339L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMissionStatus missionStatus = new QMissionStatus("missionStatus");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMission mission;

    public final EnumPath<MissionStatusType> missionStatusType = createEnum("missionStatusType", MissionStatusType.class);

    public final DateTimePath<java.time.LocalDateTime> modifyDate = createDateTime("modifyDate", java.time.LocalDateTime.class);

    public QMissionStatus(String variable) {
        this(MissionStatus.class, forVariable(variable), INITS);
    }

    public QMissionStatus(Path<? extends MissionStatus> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMissionStatus(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMissionStatus(PathMetadata metadata, PathInits inits) {
        this(MissionStatus.class, metadata, inits);
    }

    public QMissionStatus(Class<? extends MissionStatus> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mission = inits.isInitialized("mission") ? new QMission(forProperty("mission"), inits.get("mission")) : null;
    }

}

