package com.example.financemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "FinanceDB";
    public static final String TABLE_NAME = "transactions";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, type TEXT, amount INTEGER, note TEXT)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertTransaction(String type, int amount, String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("type", type);
        cv.put("amount", amount);
        cv.put("note", note);
        db.insert(TABLE_NAME, null, cv);
    }

    public Cursor getAllTransactions() {
        return this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}
