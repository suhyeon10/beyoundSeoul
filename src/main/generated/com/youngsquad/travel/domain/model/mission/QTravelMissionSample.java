package com.youngsquad.travel.domain.model.mission;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTravelMissionSample is a Querydsl query type for TravelMissionSample
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTravelMissionSample extends EntityPathBase<TravelMissionSample> {

    private static final long serialVersionUID = 367494656L;

    public static final QTravelMissionSample travelMissionSample = new QTravelMissionSample("travelMissionSample");

    public final StringPath address = createString("address");

    public final StringPath detail = createString("detail");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public final EnumPath<MissionCategory> missionCategory = createEnum("missionCategory", MissionCategory.class);

    public final StringPath title = createString("title");

    public final StringPath travelMissionDestination = createString("travelMissionDestination");

    public QTravelMissionSample(String variable) {
        super(TravelMissionSample.class, forVariable(variable));
    }

    public QTravelMissionSample(Path<? extends TravelMissionSample> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTravelMissionSample(PathMetadata metadata) {
        super(TravelMissionSample.class, metadata);
    }

}

