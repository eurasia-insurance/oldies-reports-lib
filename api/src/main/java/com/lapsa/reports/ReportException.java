package com.lapsa.reports;

public class ReportException extends RuntimeException {
    private static final long serialVersionUID = 2485194574338651823L;

    public ReportException() {
	super();
    }

    public ReportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

    public ReportException(String message, Throwable cause) {
	super(message, cause);
    }

    public ReportException(String message) {
	super(message);
    }

    public ReportException(Throwable cause) {
	super(cause);
    }

}
