package com.youngsquad.record.application;

import java.util.List;

public class RecordCursorResult<T> {
    private List<T> values;
    private Boolean hasNext;

    public RecordCursorResult(List<T> values, Boolean hasNext) {
        this.values = values;
        this.hasNext = hasNext;
    }
}
