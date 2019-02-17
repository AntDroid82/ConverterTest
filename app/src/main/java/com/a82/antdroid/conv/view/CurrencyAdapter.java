package com.a82.antdroid.conv.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.a82.antdroid.conv.entity.Currency;

import java.util.List;

public final class CurrencyAdapter extends BaseAdapter {
    private final List<Currency> data;
    private final LayoutInflater inflater;

    public CurrencyAdapter(final Context context, final List<Currency> data) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Currency getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
        }
        final Currency item = getItem(position);
        ((TextView) view).setText(item.getName());

        return view;
    }
}
