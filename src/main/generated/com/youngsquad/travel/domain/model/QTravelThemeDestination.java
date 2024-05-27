package com.youngsquad.travel.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTravelThemeDestination is a Querydsl query type for TravelThemeDestination
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTravelThemeDestination extends EntityPathBase<TravelThemeDestination> {

    private static final long serialVersionUID = 1935886841L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTravelThemeDestination travelThemeDestination = new QTravelThemeDestination("travelThemeDestination");

    public final StringPath destination = createString("destination");

    public final StringPath detal = createString("detal");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath route = createString("route");

    public final QTravelTheme travelTheme;

    public QTravelThemeDestination(String variable) {
        this(TravelThemeDestination.class, forVariable(variable), INITS);
    }

    public QTravelThemeDestination(Path<? extends TravelThemeDestination> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTravelThemeDestination(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTravelThemeDestination(PathMetadata metadata, PathInits inits) {
        this(TravelThemeDestination.class, metadata, inits);
    }

    public QTravelThemeDestination(Class<? extends TravelThemeDestination> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.travelTheme = inits.isInitialized("travelTheme") ? new QTravelTheme(forProperty("travelTheme")) : null;
    }

}

