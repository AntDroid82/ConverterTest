package com.a82.antdroid.conv.model.remote;

import com.a82.antdroid.conv.entity.Currency;

import java.util.List;

public interface RemoteDataProviderListener {
    void onLoadingFailed();

    void onLoadingSuccess(final List<Currency> data);
}
