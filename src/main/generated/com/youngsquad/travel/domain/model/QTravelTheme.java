package com.youngsquad.travel.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTravelTheme is a Querydsl query type for TravelTheme
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTravelTheme extends EntityPathBase<TravelTheme> {

    private static final long serialVersionUID = 1356528341L;

    public static final QTravelTheme travelTheme = new QTravelTheme("travelTheme");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath route = createString("route");

    public QTravelTheme(String variable) {
        super(TravelTheme.class, forVariable(variable));
    }

    public QTravelTheme(Path<? extends TravelTheme> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTravelTheme(PathMetadata metadata) {
        super(TravelTheme.class, metadata);
    }

}

