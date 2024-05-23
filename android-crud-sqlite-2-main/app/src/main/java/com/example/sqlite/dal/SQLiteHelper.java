package com.example.sqlite.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sqlite.model.Item;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ChiTieu.db";
    private static int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String sql = "CREATE TABLE items(" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "title TEXT," + "category TEXT," + "price TEXT," + "date TEXT)";

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
    public List<Item> getAll() {
        List<Item> list = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        String orderBy = "date DESC";

        Cursor cursor = database.query("items", null, null, null, null, null, orderBy);

        while (cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String category = cursor.getString(2);
            String price = cursor.getString(3);
            String date = cursor.getString(4);

            list.add(new Item(id, title, category, price, date));
        }

        return list;
    }

    // insert item
    public long addItem(@NonNull Item item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", item.getTitle());
        contentValues.put("category", item.getCategory());
        contentValues.put("price", item.getPrice());
        contentValues.put("date", item.getDate());

        SQLiteDatabase database = getWritableDatabase();
        return database.insert("items", null, contentValues);
    }

    // update item
    public int update(Item item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", item.getTitle());
        contentValues.put("category", item.getCategory());
        contentValues.put("price", item.getPrice());
        contentValues.put("date", item.getDate());

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

    // get items by date
    public List<Item> getByDate(String date) {
        List<Item> list = new ArrayList<>();

        String whereClause = "date like ?";
        String[] whereArgs = {date};

        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.query("items", null, whereClause, whereArgs, null, null, null);

        while (cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String category = cursor.getString(2);
            String price = cursor.getString(3);

            list.add(new Item(id, title, category, price, date));
        }

        return list;
    }

    public List<Item> getItemsByTitle(String key) {
        List<Item> list = new ArrayList<>();

        String whereClause = "title like ?";
        String[] whereArgs = {"%" + key + "%"};

        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.query("items", null, whereClause, whereArgs, null, null, null);

        while (cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String category = cursor.getString(2);
            String price = cursor.getString(3);
            String date = cursor.getString(4);

            list.add(new Item(id, title, category, price, date));
        }

        return list;
    }

    public List<Item> getItemsByCategory(String category) {
        List<Item> list = new ArrayList<>();

        String whereClause = "category like ?";
        String[] whereArgs = {category};

        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.query("items", null, whereClause, whereArgs, null, null, null);

        while (cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
//            String category = cursor.getString(2);
            String price = cursor.getString(3);
            String date = cursor.getString(4);

            list.add(new Item(id, title, category, price, date));
        }

        return list;
    }

    public List<Item> getItemsByTimePeriod(String from, String to) {
        List<Item> list = new ArrayList<>();

        String whereClause = "date BETWEEN ? AND ?";
        String[] whereArgs = {from.trim(), to.trim()};

        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.query("items", null, whereClause, whereArgs, null, null, null);

        while (cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String category = cursor.getString(2);
            String price = cursor.getString(3);
            String date = cursor.getString(4);

            list.add(new Item(id, title, category, price, date));
        }

        return list;
    }
}
