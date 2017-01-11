package com.lapsa.reports;

import java.io.InputStream;

public interface ReportData {
    String contentType();

    InputStream contentAsInputStream();
}
