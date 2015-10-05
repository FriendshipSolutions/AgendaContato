package com.example.william.agendacontato.database;

/**
 * Created by william on 12/08/2015.
 */

import android.content.Context;
import android.database.sqlite.*;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, "Agenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptSQL.getCreateContato());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
