package com.lapsa.reports.table.impl;

import java.util.Currency;

import com.lapsa.fin.FinCurrency;
import com.lapsa.reports.table.AmountValueCell;

public class DefaultAmountValueCell implements AmountValueCell {

    private final Double value;
    private final Currency currency;

    public DefaultAmountValueCell(Number value, Currency currency) {
	this.value = value == null ? null : value.doubleValue();
	this.currency = currency;
    }

    public DefaultAmountValueCell(Number value, String currencyCode) {
	this.value = value == null ? null : value.doubleValue();
	if (currencyCode == null)
	    this.currency = null;
	else {
	    Currency cur = null;
	    try {
		cur = Currency.getInstance(currencyCode);
	    } catch (Throwable e) {
	    }
	    this.currency = cur;
	}
    }

    public DefaultAmountValueCell(Number value, FinCurrency currency) {
	this.value = value == null ? null : value.doubleValue();
	if (currency == null)
	    this.currency = null;
	else {
	    Currency cur = null;
	    try {
		cur = Currency.getInstance(currency.name());
	    } catch (Throwable e) {
	    }
	    this.currency = cur;
	}

    }

    @Override
    public Double getValue() {
	return value;
    }

    @Override
    public Currency getCurrency() {
	return currency;
    }

}
