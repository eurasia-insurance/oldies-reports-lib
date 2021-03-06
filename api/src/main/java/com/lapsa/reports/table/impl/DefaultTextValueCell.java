package com.lapsa.reports.table.impl;

import com.lapsa.reports.table.TextValueCell;

public class DefaultTextValueCell implements TextValueCell {

    private final String value;

    public DefaultTextValueCell(String value) {
	this.value = value;
    }

    public DefaultTextValueCell(Object value) {
	this.value = value == null ? null : value.toString();
    }

    @Override
    public String getValue() {
	return value;
    }

}
