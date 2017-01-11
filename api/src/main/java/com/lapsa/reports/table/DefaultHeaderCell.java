package com.lapsa.reports.table;

public class DefaultHeaderCell implements HeaderCell {

    private final String caption;

    public DefaultHeaderCell(String caption) {
	this.caption = caption;
    }

    public DefaultHeaderCell(Object caption) {
	this.caption = caption == null ? null : caption.toString();
    }

    @Override
    public String getCaption() {
	return caption;
    }

}
