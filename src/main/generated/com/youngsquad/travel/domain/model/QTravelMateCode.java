package com.youngsquad.travel.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTravelMateCode is a Querydsl query type for TravelMateCode
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTravelMateCode extends EntityPathBase<TravelMateCode> {

    private static final long serialVersionUID = 177335814L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTravelMateCode travelMateCode = new QTravelMateCode("travelMateCode");

    public final DateTimePath<java.time.LocalDateTime> expireTime = createDateTime("expireTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath mateCode = createString("mateCode");

    public final QTravel travel;

    public QTravelMateCode(String variable) {
        this(TravelMateCode.class, forVariable(variable), INITS);
    }

    public QTravelMateCode(Path<? extends TravelMateCode> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTravelMateCode(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTravelMateCode(PathMetadata metadata, PathInits inits) {
        this(TravelMateCode.class, metadata, inits);
    }

    public QTravelMateCode(Class<? extends TravelMateCode> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.travel = inits.isInitialized("travel") ? new QTravel(forProperty("travel")) : null;
    }

}

