package com.a82.antdroid.conv;

import com.a82.antdroid.conv.entity.Currency;
import com.a82.antdroid.conv.model.ConverterModel;
import com.a82.antdroid.conv.presenter.ConverterPresenterImpl;
import com.a82.antdroid.conv.view.ConverterView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConverterPresenterTest {
    private ConverterPresenterImpl presenter;

    @Before
    public void init() {
        final ConverterModel model = new ModelMock();
        final ConverterView view = new ViewMock();
        presenter = new ConverterPresenterImpl(model, view);
    }

    @Test
    public void calculate() {
        Currency from = new Currency("Dollars", "USD", 1, 65.2);
        Currency to = new Currency("Rubles", "RU", 1, 1);
        double sum = 2;
        double result = presenter.calculate(from, to, sum);
        Assert.assertEquals(130.4, result, 0.0);
    }
}
