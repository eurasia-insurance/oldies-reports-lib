package com.lapsa.reports.table;

public class DefaultIntegerNumberValueCell implements IntegerNumberValueCell {

    private final Integer value;

    public DefaultIntegerNumberValueCell(Number value) {
	this.value = value == null ? null : value.intValue();
    }

    @Override
    public Integer getValue() {
	return value;
    }

}
