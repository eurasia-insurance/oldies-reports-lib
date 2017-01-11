package com.lapsa.reports;

import java.util.ServiceLoader;

public final class ReportGeneratorFactory {
    private ReportGeneratorFactory() {
    }

    public static ReportGenerator createReportGenerator(String reportType) {
	final ServiceLoader<ReportGenerator> factorySPI = ServiceLoader.load(ReportGenerator.class);
	for (final ReportGenerator factory : factorySPI)
	    for (String supportedType : factory.getSupportedTypes())
		if (supportedType.equals(reportType))
		    return factory;
	throw new ReportException(String.format("There is no any registered %1$s service provider with type '%2$s'",
		ReportGenerator.class.getSimpleName(), reportType));
    }
}
