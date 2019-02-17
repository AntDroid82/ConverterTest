package com.a82.antdroid.conv.view;

import com.a82.antdroid.conv.entity.Currency;

import java.util.List;

public interface ConverterView {
    void setupCurrency(final List<Currency> data);

    void setResult(final double result);
}
