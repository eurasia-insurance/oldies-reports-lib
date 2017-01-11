package com.lapsa.reports;

import com.lapsa.reports.table.TableModel;

public interface ReportGenerator {
    String[] getSupportedTypes();

    ReportData generateTableReport(TableModel tableModel);
}
