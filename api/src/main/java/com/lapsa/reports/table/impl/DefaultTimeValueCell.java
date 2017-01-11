package com.lapsa.reports.table.impl;

import java.time.Instant;
import java.time.LocalTime;
import java.util.Date;

import com.lapsa.reports.table.TimeValueCell;

public class DefaultTimeValueCell implements TimeValueCell {

    private final LocalTime value;

    public DefaultTimeValueCell(Date value) {
	this.value = value == null ? null : LocalTime.from(value.toInstant());
    }

    public DefaultTimeValueCell(Instant value) {
	this.value = value == null ? null : LocalTime.from(value);
    }

    public DefaultTimeValueCell(LocalTime value) {
	this.value = value;
    }

    @Override
    public LocalTime getValue() {
	return value;
    }

}
