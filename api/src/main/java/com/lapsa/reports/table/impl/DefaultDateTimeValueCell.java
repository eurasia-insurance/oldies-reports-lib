package com.lapsa.reports.table.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import com.lapsa.reports.table.DateTimeValueCell;

public class DefaultDateTimeValueCell implements DateTimeValueCell {

    private final LocalDateTime value;

    public DefaultDateTimeValueCell(Date value) {
	this.value = value == null ? null : LocalDateTime.ofInstant(value.toInstant(), ZoneId.systemDefault());
    }

    public DefaultDateTimeValueCell(Calendar value) {
	this.value = value == null ? null : LocalDateTime.ofInstant(value.toInstant(), ZoneId.systemDefault());
    }

    public DefaultDateTimeValueCell(Instant value) {
	this.value = value == null ? null : LocalDateTime.ofInstant(value, ZoneId.systemDefault());
    }

    public DefaultDateTimeValueCell(LocalDateTime value) {
	this.value = value;
    }

    @Override
    public LocalDateTime getValue() {
	return value;
    }

}
