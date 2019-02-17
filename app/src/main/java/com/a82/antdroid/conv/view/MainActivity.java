package com.a82.antdroid.conv.view;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.a82.antdroid.conv.R;
import com.a82.antdroid.conv.entity.Currency;
import com.a82.antdroid.conv.model.CurrencyRepo;
import com.a82.antdroid.conv.model.db.LocalDataProvider;
import com.a82.antdroid.conv.model.remote.RemoteDataProvider;
import com.a82.antdroid.conv.presenter.ConverterPresenter;
import com.a82.antdroid.conv.presenter.ConverterPresenterImpl;

import java.util.List;

public final class MainActivity extends AppCompatActivity implements ConverterView {

    private ConverterPresenter presenter;

    private Spinner spinnerFrom;
    private Spinner spinnerTo;
    private TextInputEditText etSum;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerFrom = findViewById(R.id.spinner_from);
        spinnerTo = findViewById(R.id.spinner_to);
        etSum = findViewById(R.id.et_sum);
        final Button btnCalculate = findViewById(R.id.btn_calculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCalculateBtnClicked();
            }
        });
        tvResult = findViewById(R.id.tv_result);
        init();
        presenter.onViewCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onViewDestroyed();
        }
    }

    private void init() {
        final LocalDataProvider localDataProvider = new LocalDataProvider(this);
        final RemoteDataProvider remoteDataProvider = new RemoteDataProvider();

        final CurrencyRepo repo = new CurrencyRepo(localDataProvider, remoteDataProvider);
        presenter = new ConverterPresenterImpl(repo, this);
        repo.setPresenter(presenter);
    }

    private void onCalculateBtnClicked() {
        final Currency from = (Currency) spinnerFrom.getSelectedItem();
        final Currency to = (Currency) spinnerTo.getSelectedItem();
        final String sumText = etSum.getText().toString();
        final double sum = sumText.isEmpty() ? 0.0 : Double.valueOf(sumText);
        presenter.onCalculateBtnClicked(from, to, sum);
    }

    @Override
    public void setupCurrency(final List<Currency> data) {
        setupSpinner(spinnerFrom, data);
        setupSpinner(spinnerTo, data);
    }

    private void setupSpinner(final Spinner spinner, final List<Currency> data) {
        final CurrencyAdapter adapter = new CurrencyAdapter(this, data);
        spinner.setAdapter(adapter);
    }

    @Override
    public void setResult(final double result) {
        tvResult.setText(getString(R.string.result, result));
    }
}
