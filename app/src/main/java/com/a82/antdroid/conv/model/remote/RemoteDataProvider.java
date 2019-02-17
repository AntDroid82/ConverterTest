package com.a82.antdroid.conv.model.remote;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.a82.antdroid.conv.entity.Currency;
import com.a82.antdroid.conv.entity.ValCurs;
import com.a82.antdroid.conv.entity.Valute;

import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public final class RemoteDataProvider {
    private RemoteDataProviderListener listener;
    private LoadCurrencyTask task = null;

    public void setListener(final RemoteDataProviderListener listener) {
        this.listener = listener;
    }

    public void loadCurrency() {
        task = new LoadCurrencyTask();
        task.execute();
    }

    public void release() {
        if (task != null) {
            task.cancel(false);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private final class LoadCurrencyTask extends AsyncTask<String, Void, List<Currency>> {

        @Override
        protected List<Currency> doInBackground(String... strings) {
            HttpURLConnection connection = null;
            try {
                final URL url = new URL("http://www.cbr.ru/scripts/XML_daily.asp");
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                final BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "windows-1251"));
                final StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line)
                    .append("\\n");
                }
                br.close();
                return convertResponse(sb.toString());
            } catch (IOException e) {
                return null;
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }

        private List<Currency> convertResponse(final String response) {
            final Reader reader = new StringReader(response);
            final Persister serializer = new Persister();
            try {
                final List<Currency> result = new ArrayList<>();
                final ValCurs vals = serializer.read(ValCurs.class, reader, false);
                for (Valute valute : vals.getValutes()) {
                    result.add(Currency.fromValute(valute));
                }
                return result;
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Currency> result) {
            if ((result != null)
                    && !result.isEmpty()) {
                listener.onLoadingSuccess(result);
            } else {
                listener.onLoadingFailed();
            }
        }
    }
}
