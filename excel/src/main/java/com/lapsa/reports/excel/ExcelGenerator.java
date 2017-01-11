package com.lapsa.reports.excel;

import com.lapsa.reports.ReportData;
import com.lapsa.reports.ReportGenerator;
import com.lapsa.reports.table.TableModel;

public class ExcelGenerator implements ReportGenerator {

    private static final String[] SUPPORTED_TYPES = new String[] { "excel" };

    @Override
    public String[] getSupportedTypes() {
	return SUPPORTED_TYPES;
    }

    @Override
    public ReportData generateTableReport(TableModel tableModel) {
	return new ExcelTableReport(tableModel).getReportData();
    }

}
