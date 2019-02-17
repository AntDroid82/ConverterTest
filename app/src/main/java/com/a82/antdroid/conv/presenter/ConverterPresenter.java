package com.a82.antdroid.conv.presenter;

import com.a82.antdroid.conv.entity.Currency;

import java.util.List;

public interface ConverterPresenter {

    void onViewCreate();

    void onViewDestroyed();

    void onCalculateBtnClicked(final Currency from, final Currency to, final double value);

    void onDataObtained(final List<Currency> data);

}
