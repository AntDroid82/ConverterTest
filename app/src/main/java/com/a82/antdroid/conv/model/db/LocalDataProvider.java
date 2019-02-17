package com.a82.antdroid.conv.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.a82.antdroid.conv.entity.Currency;

import java.util.ArrayList;
import java.util.List;

public final class LocalDataProvider {

    private final SQLiteDatabase sqLiteDatabase;

    public LocalDataProvider(final Context context) {
        final CurrencyDataBase db = new CurrencyDataBase(context);
        sqLiteDatabase = db.getWritableDatabase();
    }

    public void storeData(final List<Currency> data) {
        sqLiteDatabase.beginTransaction();

        sqLiteDatabase.execSQL("delete from " + CurrencyDataBase.TABLE_NAME);
        final ContentValues values = new ContentValues();
        for (Currency item : data) {
            values.put(CurrencyDataBase.COLUMN_NAME, item.getName());
            values.put(CurrencyDataBase.COLUMN_CODE, item.getCode());
            values.put(CurrencyDataBase.COLUMN_NOMINAL, item.getNominal());
            values.put(CurrencyDataBase.COLUMN_VALUE, item.getValue());
            sqLiteDatabase.insert(CurrencyDataBase.TABLE_NAME, null, values);
        }

        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();
    }

    public List<Currency> getAll() {
        final List<Currency> result = new ArrayList<>();
        final Cursor cursor = sqLiteDatabase.query(CurrencyDataBase.TABLE_NAME,
                null, null, null,
                null, null, null);
        final int nameColumnIndex = cursor.getColumnIndex(CurrencyDataBase.COLUMN_NAME);
        final int codeColumnIndex = cursor.getColumnIndex(CurrencyDataBase.COLUMN_CODE);
        final int nominalColumnIndex = cursor.getColumnIndex(CurrencyDataBase.COLUMN_NOMINAL);
        final int valueColumnIndex = cursor.getColumnIndex(CurrencyDataBase.COLUMN_VALUE);
        while (cursor.moveToNext()) {
            final String name = cursor.getString(nameColumnIndex);
            final String code = cursor.getString(codeColumnIndex);
            final int nominal = cursor.getInt(nominalColumnIndex);
            final double value = cursor.getDouble(valueColumnIndex);
            result.add(new Currency(name, code, nominal, value));
        }
        cursor.close();
        return result;
    }

    public void release() {
        sqLiteDatabase.close();
    }
}
