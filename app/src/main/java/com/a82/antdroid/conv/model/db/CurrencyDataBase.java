package com.a82.antdroid.conv.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public final class CurrencyDataBase extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "currency";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_NOMINAL = "nominal";
    public static final String COLUMN_VALUE = "value";
    public static final String COLUMN_CODE = "code";

    public CurrencyDataBase(final Context context) {
        super(context, "currencyDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ("
                + "id integer primary key autoincrement,"
                + COLUMN_NAME + " text,"
                + COLUMN_NOMINAL + " integer,"
                + COLUMN_VALUE + " double,"
                + COLUMN_CODE + " text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }
}
