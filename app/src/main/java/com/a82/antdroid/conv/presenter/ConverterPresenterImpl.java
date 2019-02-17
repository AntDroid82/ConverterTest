package com.a82.antdroid.conv.presenter;

import com.a82.antdroid.conv.entity.Currency;
import com.a82.antdroid.conv.model.ConverterModel;
import com.a82.antdroid.conv.view.ConverterView;

import java.util.List;

public final class ConverterPresenterImpl implements ConverterPresenter {
    private final ConverterModel model;
    private ConverterView view;

    public ConverterPresenterImpl(final ConverterModel model, final ConverterView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void onViewCreate() {
        model.requestData();
    }

    @Override
    public void onViewDestroyed() {
        view = null;
        model.releaseResources();
    }

    @Override
    public void onCalculateBtnClicked(final Currency from, final Currency to, final double value) {
        double result = calculate(from, to, value);
        if (view != null) {
            view.setResult(result);
        }
    }

    public double calculate(final Currency from, final Currency to, final double value) {
        final double fromRate = from.getValue() / from.getNominal();
        final double toRate = to.getValue() / to.getNominal();
        return fromRate * value / toRate;
    }

    @Override
    public void onDataObtained(final List<Currency> data) {
        if (view != null) {
            view.setupCurrency(data);
        }
    }
}
