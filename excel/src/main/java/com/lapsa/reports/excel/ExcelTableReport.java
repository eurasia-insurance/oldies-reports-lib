package com.lapsa.reports.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Currency;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import com.lapsa.reports.ReportData;
import com.lapsa.reports.ReportException;
import com.lapsa.reports.table.AmountValueCell;
import com.lapsa.reports.table.DateTimeValueCell;
import com.lapsa.reports.table.DateValueCell;
import com.lapsa.reports.table.HeaderCell;
import com.lapsa.reports.table.IntegerNumberValueCell;
import com.lapsa.reports.table.TableModel;
import com.lapsa.reports.table.TextValueCell;
import com.lapsa.reports.table.TimeValueCell;
import com.lapsa.reports.table.ValueCell;
import com.lapsa.reports.table.ValueRow;

public class ExcelTableReport {

    private static final BorderStyle DEFAULT_BORDER_STYLE = BorderStyle.THIN;

    protected static final String CONTENT_TYPE = "application/vnd.ms-excel";

    private HSSFWorkbook workbook;
    private HSSFSheet sheet;

    private HSSFCellStyle headerCellStyle;
    private HSSFCellStyle textCellStyle;
    private HSSFCellStyle amountCellType;
    private HSSFCellStyle numericCellType;

    private HSSFCellStyle dateTimeCellType;
    private HSSFCellStyle dateCellType;
    private HSSFCellStyle timeCellType;

    public ExcelTableReport(TableModel tableModel) {
	workbook = new HSSFWorkbook();

	sheet = workbook.createSheet("report");

	int rnum = 1;

	{
	    int cnum = 1;
	    HSSFRow hssrow = sheet.createRow(rnum++);
	    for (HeaderCell cell : tableModel.getHeaderRow())
		createHeaderCell(hssrow, cnum++, cell.getCaption());
	}

	{
	    for (ValueRow row : tableModel) {
		int cnum = 1;
		HSSFRow hssrow = sheet.createRow(rnum++);
		for (ValueCell<?> cell : row) {
		    if (cell instanceof TextValueCell)
			createTextCell(hssrow, cnum, ((TextValueCell) cell).getValue());
		    else if (cell instanceof DateValueCell)
			createDateCell(hssrow, cnum, ((DateValueCell) cell).getValue());
		    else if (cell instanceof TimeValueCell)
			createTimeCell(hssrow, cnum, ((TimeValueCell) cell).getValue());
		    else if (cell instanceof DateTimeValueCell)
			createDateTimeCell(hssrow, cnum, ((DateTimeValueCell) cell).getValue());
		    else if (cell instanceof IntegerNumberValueCell)
			createIntegerNumberCell(hssrow, cnum, ((IntegerNumberValueCell) cell).getValue());
		    else if (cell instanceof AmountValueCell)
			createAmountCell(hssrow, cnum, ((AmountValueCell) cell).getValue(),
				((AmountValueCell) cell).getCurrency());
		    cnum++;
		}
	    }
	}

	for (int i = 1; i <= 14; i++)
	    sheet.autoSizeColumn(i);
    }

    public ReportData getReportData() {
	try {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    workbook.write(baos);
	    final InputStream is = new ByteArrayInputStream(baos.toByteArray());

	    return new ReportData() {
		@Override
		public String contentType() {
		    return CONTENT_TYPE;
		}

		@Override
		public InputStream contentAsInputStream() {
		    return is;
		}
	    };

	} catch (IOException e) {
	    throw new ReportException(String.format("Error generating"));
	}
    }

    private HSSFCell createIntegerNumberCell(HSSFRow row, int number, Integer numberValue) {
	if (numericCellType == null) {
	    numericCellType = workbook.createCellStyle();
	    numericCellType.setVerticalAlignment(VerticalAlignment.TOP);
	    numericCellType.setBorderLeft(DEFAULT_BORDER_STYLE);
	    numericCellType.setBorderTop(DEFAULT_BORDER_STYLE);
	    numericCellType.setBorderRight(DEFAULT_BORDER_STYLE);
	    numericCellType.setBorderBottom(DEFAULT_BORDER_STYLE);
	}

	HSSFCell cell = row.createCell(number, CellType.NUMERIC);
	cell.setCellStyle(numericCellType);
	if (numberValue != null)
	    cell.setCellValue(numberValue);
	else
	    cell.setCellValue("");
	return cell;
    }

    private HSSFCell createTextCell(HSSFRow row, int number, String text) {
	if (textCellStyle == null) {
	    textCellStyle = workbook.createCellStyle();
	    textCellStyle.setAlignment(HorizontalAlignment.LEFT);
	    textCellStyle.setVerticalAlignment(VerticalAlignment.TOP);
	    textCellStyle.setWrapText(false);
	    textCellStyle.setBorderLeft(DEFAULT_BORDER_STYLE);
	    textCellStyle.setBorderTop(DEFAULT_BORDER_STYLE);
	    textCellStyle.setBorderRight(DEFAULT_BORDER_STYLE);
	    textCellStyle.setBorderBottom(DEFAULT_BORDER_STYLE);
	}
	HSSFCell cell = row.createCell(number, CellType.STRING);
	cell.setCellStyle(textCellStyle);
	if (text != null)
	    cell.setCellValue(text);
	else
	    cell.setCellValue("");
	return cell;
    }

    private HSSFCell createHeaderCell(HSSFRow row, int number, String caption) {
	if (headerCellStyle == null) {
	    headerCellStyle = workbook.createCellStyle();
	    headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
	    headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
	    headerCellStyle.setWrapText(true);
	    HSSFFont headerFont = workbook.createFont();
	    headerFont.setBold(true);
	    headerCellStyle.setFont(headerFont);
	    headerCellStyle.setBorderLeft(DEFAULT_BORDER_STYLE);
	    headerCellStyle.setBorderTop(DEFAULT_BORDER_STYLE);
	    headerCellStyle.setBorderRight(DEFAULT_BORDER_STYLE);
	    headerCellStyle.setBorderBottom(DEFAULT_BORDER_STYLE);
	}
	HSSFCell cell = row.createCell(number, CellType.STRING);
	cell.setCellStyle(headerCellStyle);
	if (caption != null)
	    cell.setCellValue(caption);
	else
	    cell.setCellValue("");
	return cell;
    }

    private HSSFCell createAmountCell(HSSFRow row, int number, Double amount, Currency currency) {
	if (amountCellType == null) {
	    HSSFDataFormat dataFormat = workbook.createDataFormat();
	    amountCellType = workbook.createCellStyle();
	    amountCellType.setVerticalAlignment(VerticalAlignment.TOP);
	    amountCellType.setDataFormat(dataFormat.getFormat("# ##0.00"));
	    amountCellType.setBorderLeft(DEFAULT_BORDER_STYLE);
	    amountCellType.setBorderTop(DEFAULT_BORDER_STYLE);
	    amountCellType.setBorderRight(DEFAULT_BORDER_STYLE);
	    amountCellType.setBorderBottom(DEFAULT_BORDER_STYLE);
	}
	HSSFCell cell = row.createCell(number, CellType.NUMERIC);
	cell.setCellStyle(amountCellType);
	if (amount != null)
	    cell.setCellValue(amount);
	else
	    cell.setCellValue("");
	return cell;
    }

    private HSSFCell createDateTimeCell(HSSFRow row, int number, LocalDateTime localDateTime) {
	if (dateTimeCellType == null) {
	    HSSFDataFormat dataFormat = workbook.createDataFormat();
	    dateTimeCellType = workbook.createCellStyle();
	    dateTimeCellType.setVerticalAlignment(VerticalAlignment.TOP);
	    dateTimeCellType.setDataFormat(dataFormat.getFormat("DD.MM.YY HH:MM:SS"));
	    dateTimeCellType.setBorderLeft(DEFAULT_BORDER_STYLE);
	    dateTimeCellType.setBorderTop(DEFAULT_BORDER_STYLE);
	    dateTimeCellType.setBorderRight(DEFAULT_BORDER_STYLE);
	    dateTimeCellType.setBorderBottom(DEFAULT_BORDER_STYLE);
	}
	HSSFCell cell = row.createCell(number, CellType.NUMERIC);
	cell.setCellStyle(dateTimeCellType);
	if (localDateTime != null)
	    cell.setCellValue(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
	else
	    cell.setCellValue("");
	return cell;
    }

    private HSSFCell createDateCell(HSSFRow row, int number, LocalDate localDate) {
	if (dateCellType == null) {
	    HSSFDataFormat dataFormat = workbook.createDataFormat();
	    dateCellType = workbook.createCellStyle();
	    dateCellType.setVerticalAlignment(VerticalAlignment.TOP);
	    dateCellType.setDataFormat(dataFormat.getFormat("DD.MM.YY"));
	    dateCellType.setBorderLeft(DEFAULT_BORDER_STYLE);
	    dateCellType.setBorderTop(DEFAULT_BORDER_STYLE);
	    dateCellType.setBorderRight(DEFAULT_BORDER_STYLE);
	    dateCellType.setBorderBottom(DEFAULT_BORDER_STYLE);
	}
	HSSFCell cell = row.createCell(number, CellType.NUMERIC);
	cell.setCellStyle(dateCellType);
	if (localDate != null)
	    cell.setCellValue(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
	else
	    cell.setCellValue("");
	return cell;
    }

    private HSSFCell createTimeCell(HSSFRow row, int number, LocalTime localTime) {
	if (timeCellType == null) {
	    HSSFDataFormat dataFormat = workbook.createDataFormat();
	    timeCellType = workbook.createCellStyle();
	    timeCellType.setVerticalAlignment(VerticalAlignment.TOP);
	    timeCellType.setDataFormat(dataFormat.getFormat("HH:MM:SS"));
	    timeCellType.setBorderLeft(DEFAULT_BORDER_STYLE);
	    timeCellType.setBorderTop(DEFAULT_BORDER_STYLE);
	    timeCellType.setBorderRight(DEFAULT_BORDER_STYLE);
	    timeCellType.setBorderBottom(DEFAULT_BORDER_STYLE);
	}
	HSSFCell cell = row.createCell(number, CellType.NUMERIC);
	cell.setCellStyle(timeCellType);
	if (localTime != null)
	    cell.setCellValue(Date.from(localTime.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant()));
	else
	    cell.setCellValue("");
	return cell;
    }
}
