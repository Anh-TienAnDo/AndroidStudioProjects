package com.track_management.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.track_management.model.Item;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "track_management.db";
    private static int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

//    được gọi khi cơ sở dữ liệu được tạo ra lần đầu tiên
    @Override
    public void onCreate(SQLiteDatabase database) {
//        bảng mới có tên "items" được tạo ra với các cột id, name, singer, album, type và isFavourite.
        String sql = "CREATE TABLE items(" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT," + "singer TEXT," + "album TEXT," + "type TEXT," + "isFavourite INTEGER)";

        database.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    //    truy vấn tất cả các mục từ bảng "items" và trả về danh sách các đối tượng Item.
    public List<Item> getAll() {
        List<Item> list = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
//        String orderBy = "date DESC";

//        truy vấn tất cả các mục từ bảng "items"
        Cursor cursor = database.query("items", null, null, null, null, null, null);

        while (cursor != null && cursor.moveToNext()) {
//            lấy theo cột 0->n
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String singer = cursor.getString(2);
            String album = cursor.getString(3);
            String type = cursor.getString(4);
            Boolean isFavourite = cursor.getInt(5) == 1;

//            thêm vào danh sách items
            list.add(new Item(id, name, singer, album, type, isFavourite));
        }

        return list;
    }

    // insert item: get data -> put, database.insert
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
        List<Item> list = new ArrayList<>();

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

//    public List<Item> getItemsByTimePeriod(String from, String to) {
//        List<Item> list = new ArrayList<>();
//
//        String whereClause = "date BETWEEN ? AND ?";
//        String[] whereArgs = {from.trim(), to.trim()};
//
//        SQLiteDatabase database = getReadableDatabase();
//
//        Cursor cursor = database.query("items", null, whereClause, whereArgs, null, null, null);
//
//        while (cursor != null && cursor.moveToNext()) {
//            int id = cursor.getInt(0);
//            String title = cursor.getString(1);
//            String category = cursor.getString(2);
//            String price = cursor.getString(3);
//            String date = cursor.getString(4);
//
//            list.add(new Item(id, title, category, price, date));
//        }
//
//        return list;
//    }
}
