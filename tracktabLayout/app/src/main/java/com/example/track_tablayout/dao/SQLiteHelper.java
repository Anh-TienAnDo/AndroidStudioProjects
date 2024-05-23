package com.example.track_tablayout.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.track_tablayout.model.Item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "track_management.db";
    private static int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String sql = "CREATE TABLE items(" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT," + "singer TEXT," + "album TEXT," + "type TEXT," + "isFavourite INTEGER)";
        database.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        String drop_table = String.format("DROP TABLE IF EXISTS %s", DATABASE_NAME);
        database.execSQL(drop_table);
        onCreate(database);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    // get all items sorted in descending order by date
    public List<Item> getAll() {
        List<Item> list = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
//        String orderBy = "date DESC";

        Cursor cursor = database.query("items", null, null, null, null, null, null);

        while (cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String singer = cursor.getString(2);
            String album = cursor.getString(3);
            String type = cursor.getString(4);
            Boolean isFavourite = cursor.getInt(5) == 1;

            list.add(new Item(id, name, singer, album, type, isFavourite));
        }

        return list;
    }

    // insert item
    public long addItem(@NonNull Item item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", item.getName());
        contentValues.put("singer", item.getSinger());
        contentValues.put("album", item.getAlbum());
        contentValues.put("type", item.getType());
        contentValues.put("isFavourite", item.isFavourite() ? 1 : 0);

        SQLiteDatabase database = getWritableDatabase();
        return database.insert("items", null, contentValues);
    }

    // update item
    public int update(Item item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", item.getName());
        contentValues.put("singer", item.getSinger());
        contentValues.put("album", item.getAlbum());
        contentValues.put("type", item.getType());
        contentValues.put("isFavourite", item.isFavourite() ? 1 : 0);

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

    // get items by isFavourite
    public List<Item> getByIsFavourite(boolean isFavourite) {
        List<Item> list = new ArrayList<>();

        String whereClause = "isFavourite like ?";
        String[] whereArgs = {isFavourite ? "1" : "0"};

        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.query("items", null, whereClause, whereArgs, null, null, null);

        while (cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String singer = cursor.getString(2);
            String album = cursor.getString(3);
            String type = cursor.getString(4);

            list.add(new Item(id, name, singer, album, type, isFavourite));
        }

        return list;
    }

    public List<Item> getItemsByName(String key) {
        Set<Item> list = new HashSet<>();
//        List<Item> list = new ArrayList<>();

        String whereClause = "name like ?";
        String[] whereArgs = {"%" + key + "%"};

        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.query("items", null, whereClause, whereArgs, null, null, null);

        while (cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String singer = cursor.getString(2);
            String album = cursor.getString(3);
            String type = cursor.getString(4);
            boolean isFavourite = cursor.getInt(5) == 1;

            list.add(new Item(id, name, singer, album, type, isFavourite));
        }

        String whereClause1 = "singer like ?";
        String[] whereArgs1 = {"%" + key + "%"};

        SQLiteDatabase database1 = getReadableDatabase();

        Cursor cursor1 = database1.query("items", null, whereClause1, whereArgs1, null, null, null);

        while (cursor1 != null && cursor1.moveToNext()) {
            int id = cursor1.getInt(0);
            String name = cursor1.getString(1);
            String singer = cursor1.getString(2);
            String album = cursor1.getString(3);
            String type = cursor1.getString(4);
            boolean isFavourite = cursor1.getInt(5) == 1;

            list.add(new Item(id, name, singer, album, type, isFavourite));
        }
        // Convert set thành list
        List<Item> result = new ArrayList<>(list);
        return result;
    }

    public List<Item> getItemsByAuthor(String key) {
        List<Item> list = new ArrayList<>();

        String whereClause = "singer like ?";
        String[] whereArgs = {"%" + key + "%"};

        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.query("items", null, whereClause, whereArgs, null, null, null);

        while (cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String singer = cursor.getString(2);
            String album = cursor.getString(3);
            String type = cursor.getString(4);
            boolean isFavourite = cursor.getInt(5) == 1;

            list.add(new Item(id, name, singer, album, type, isFavourite));
        }

        return list;
    }

    public List<Item> getItemsByAlbum(String album) {
        List<Item> list = new ArrayList<>();

        String whereClause = "album like ?";
        String[] whereArgs = {album};

        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.query("items", null, whereClause, whereArgs, null, null, null);

        while (cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String singer = cursor.getString(2);
            String type = cursor.getString(4);
            boolean isFavourite = cursor.getInt(5) == 1;

            list.add(new Item(id, name, singer, album, type, isFavourite));
        }

        return list;
    }

    public List<Item> getItemsByType(String type) {
        List<Item> list = new ArrayList<>();

        String whereClause = "type like ?";
        String[] whereArgs = {type};

        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.query("items", null, whereClause, whereArgs, null, null, null);

        while (cursor != null && cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String singer = cursor.getString(2);
            String album = cursor.getString(3);
            boolean isFavourite = cursor.getInt(5) == 1;

            list.add(new Item(id, name, singer, album, type, isFavourite));
        }

        return list;
    }

}
