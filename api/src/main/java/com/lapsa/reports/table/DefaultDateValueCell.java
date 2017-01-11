package com.lapsa.reports.table;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class DefaultDateValueCell implements DateValueCell {

    private final LocalDate value;

    public DefaultDateValueCell(Date value) {
	this.value = value == null ? null : LocalDate.from(value.toInstant());
    }

    public DefaultDateValueCell(Instant value) {
	this.value = value == null ? null : LocalDate.from(value);
    }

    public DefaultDateValueCell(LocalDate value) {
	this.value = value;
    }

    @Override
    public LocalDate getValue() {
	return value;
    }

}
