package com.lapsa.reports.table.impl;

import com.lapsa.reports.table.BooleanValueCell;

public class DefaultBooleanValueCell implements BooleanValueCell {

    private final boolean value;

    public DefaultBooleanValueCell(boolean value) {
	this.value = value;
    }

    @Override
    public Boolean getValue() {
	return Boolean.valueOf(value);
    }

}
