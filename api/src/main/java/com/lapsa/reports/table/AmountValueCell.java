package com.lapsa.reports.table;

import java.util.Currency;

public interface AmountValueCell extends ValueCell<Double> {
    Currency getCurrency();
}
