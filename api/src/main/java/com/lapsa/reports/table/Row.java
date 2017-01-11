package com.lapsa.reports.table;

public interface Row<T extends Cell> extends Iterable<T> {
    int getCellCount();

    T getCell(int number);
}
