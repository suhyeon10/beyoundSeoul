package com.youngsquad.record.presentation.response;

import java.util.List;

public record CursorResult<T>(
        Integer totalElements,
        Long nextCursor,
        Boolean isLast,
        List<T> content
) {
}
