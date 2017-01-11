package com.lapsa.reports.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DefaultHeaderRow implements HeaderRow {

    private final List<HeaderCell> row;

    public DefaultHeaderRow(String[] captions) {
	List<HeaderCell> row = new ArrayList<>();
	for (String caption : captions) {
	    HeaderCell cell = new DefaultHeaderCell(caption);
	    row.add(cell);
	}
	this.row = Collections.unmodifiableList(row);
    }

    @Override
    public int getCellCount() {
	return row.size();
    }

    @Override
    public HeaderCell getCell(int number) {
	return row.get(number);
    }

    @Override
    public Iterator<HeaderCell> iterator() {
	return row.iterator();
    }

}
