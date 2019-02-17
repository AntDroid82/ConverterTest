package com.a82.antdroid.conv.model;

import com.a82.antdroid.conv.entity.Currency;
import com.a82.antdroid.conv.model.db.LocalDataProvider;
import com.a82.antdroid.conv.model.remote.RemoteDataProvider;
import com.a82.antdroid.conv.model.remote.RemoteDataProviderListener;
import com.a82.antdroid.conv.presenter.ConverterPresenter;

import java.util.List;

public final class CurrencyRepo implements ConverterModel, RemoteDataProviderListener {
    private ConverterPresenter presenter;

    private LocalDataProvider dbDataProvider;
    private RemoteDataProvider networkDataProvider;

    public CurrencyRepo(final LocalDataProvider dbDataProvider,
                        final RemoteDataProvider networkDataProvider) {
        this.dbDataProvider = dbDataProvider;
        this.networkDataProvider = networkDataProvider;
        this.networkDataProvider.setListener(this);
    }

    public void setPresenter(final ConverterPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void requestData() {
        networkDataProvider.loadCurrency();
    }

    @Override
    public void releaseResources() {
        networkDataProvider.release();
        dbDataProvider.release();
    }

    @Override
    public void onLoadingFailed() {
        presenter.onDataObtained(dbDataProvider.getAll());
    }

    @Override
    public void onLoadingSuccess(final List<Currency> data) {
        dbDataProvider.storeData(data);
        presenter.onDataObtained(data);
    }
}
