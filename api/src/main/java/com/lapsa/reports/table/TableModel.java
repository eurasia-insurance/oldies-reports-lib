package com.lapsa.reports.table;

public interface TableModel extends Iterable<ValueRow> {
    HeaderRow getHeaderRow();

    int getRowCount();

    ValueRow getRow(int number);

}
