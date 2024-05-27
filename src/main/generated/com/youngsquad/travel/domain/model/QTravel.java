package com.youngsquad.travel.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTravel is a Querydsl query type for Travel
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTravel extends EntityPathBase<Travel> {

    private static final long serialVersionUID = -957087916L;

    public static final QTravel travel = new QTravel("travel");

    public final DateTimePath<java.time.LocalDateTime> createAt = createDateTime("createAt", java.time.LocalDateTime.class);

    public final StringPath destination = createString("destination");

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath peopleNum = createString("peopleNum");

    public final NumberPath<Long> reader = createNumber("reader", Long.class);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final StringPath title = createString("title");

    public final NumberPath<Long> travelTheme = createNumber("travelTheme", Long.class);

    public final EnumPath<TravelWithType> travelWith = createEnum("travelWith", TravelWithType.class);

    public QTravel(String variable) {
        super(Travel.class, forVariable(variable));
    }

    public QTravel(Path<? extends Travel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTravel(PathMetadata metadata) {
        super(Travel.class, metadata);
    }

}

