package com.example.duoihinhbatchu_v2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.duoihinhbatchu_v2.models.Riddle;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DuoiHinhBatChu.db";
    private static int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String sql = "CREATE TABLE items(" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT," + "result TEXT," + "image INTEGER)";
        database.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    // get all items sorted in descending order by date
    public List<Riddle> getAll() {
        List<Riddle> riddles = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query("items", null, null, null, null, null, null);

        while (cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String result = cursor.getString(2);
            int image = cursor.getInt(3);

            riddles.add(new Riddle(id, image, name, result));
        }

        return riddles;
    }

    // insert item
    public long addItem(@NonNull Riddle item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", item.getName());
        contentValues.put("result", item.getResult());
        contentValues.put("image", item.getImage());

        SQLiteDatabase database = getWritableDatabase();
        return database.insert("items", null, contentValues);
    }

    // update item
    public int update(Riddle item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", item.getName());
        contentValues.put("result", item.getResult());
        contentValues.put("image", item.getImage());

        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(item.getId())};

        SQLiteDatabase database = getWritableDatabase();
        return database.update("items", contentValues, whereClause, whereArgs);
    }

    // delete item
    public int delete(int id) {
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(id)};

        SQLiteDatabase database = getWritableDatabase();
        return database.delete("items", whereClause, whereArgs);
    }

    public List<Riddle> getItemsByName(String key) {
        List<Riddle> list = new ArrayList<>();

        String whereClause = "name like ?";
        String[] whereArgs = {"%" + key + "%"};

        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.query("items", null, whereClause, whereArgs, null, null, null);

        while (cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String result = cursor.getString(2);
            int image = cursor.getInt(3);

            list.add(new Riddle(id, image, name, result));
        }

        return list;
    }

    public List<Riddle> getItemsByResult(String key) {
        List<Riddle> list = new ArrayList<>();

        String whereClause = "result like ?";
        String[] whereArgs = {"%" + key + "%"};

        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.query("items", null, whereClause, whereArgs, null, null, null);

        while (cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String result = cursor.getString(2);
            int image = cursor.getInt(3);

            list.add(new Riddle(id, image, name, result));
        }

        return list;
    }

}
