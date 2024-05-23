package com.example.songmanager.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.songmanager.entity.Song;

import java.util.ArrayList;
import java.util.List;

public class SongHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "song_manager.db";
    private static int DB_VERSION = 1;

    public SongHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqlite) {
        String sqlCreateSong = "CREATE TABLE song (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT, singer TEXT, album TEXT, type TEXT, love INTEGER)";
        sqlite.execSQL(sqlCreateSong);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<Song> getAllSong() {
        List<Song> songList = new ArrayList<>();
        SQLiteDatabase sqlite = getReadableDatabase();
        Cursor cursor = sqlite.query("song", null, null, null, null, null, "name");
        while (cursor != null && cursor.moveToNext()) {
            getSongRecordFromCursor(cursor, songList);
        }
        return songList;
    }

    public List<Song> getSongByName(String name) {
        List<Song> songList = new ArrayList<>();
        SQLiteDatabase sqlite = getReadableDatabase();
        String whereClause = "name like ?";
        String[] whereArgs = {"%" + name + "%"};
        Cursor cursor = sqlite.query("song", null, whereClause, whereArgs, null, null, "name");
        while (cursor != null && cursor.moveToNext()) {
            getSongRecordFromCursor(cursor, songList);
        }
        return songList;
    }

    public int getCountSongByType(String type){
        SQLiteDatabase sqlite = getReadableDatabase();
        String[] whereArgs = {type};
        Cursor cursor = sqlite.rawQuery("SELECT COUNT(*) FROM song WHERE type = ?", whereArgs);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        return count;

    }
    public List<Song> getSongByNameAndAlbum(String name, String album) {
        List<Song> songList = new ArrayList<>();
        SQLiteDatabase sqlite = getReadableDatabase();
        String whereClause = "name like ? AND album like ?";
        String[] whereArgs = {"%" + name + "%", album};
        Cursor cursor = sqlite.query("song", null, whereClause, whereArgs, null, null, "name");
        while (cursor != null && cursor.moveToNext()) {
            getSongRecordFromCursor(cursor, songList);
        }
        return songList;
    }


    public List<Song> getSongByAlbum(String album) {
        List<Song> songList = new ArrayList<>();
        SQLiteDatabase sqlite = getReadableDatabase();
        String whereClause = "album like ?";
        String[] whereArgs = {album};
        Cursor cursor = sqlite.query("song", null, whereClause, whereArgs, null, null, "name");
        while (cursor != null && cursor.moveToNext()) {
            getSongRecordFromCursor(cursor, songList);
        }
        return songList;
    }

    public Long addSong(Song song) {
        ContentValues values = new ContentValues();
        getSongValuesFromRecord(values, song);
        SQLiteDatabase sqlite = getWritableDatabase();
        return sqlite.insert("song", null, values);
    }


    public int updateSong(Song newSong, int oldId) {
        ContentValues values = new ContentValues();
        getSongValuesFromRecord(values, newSong);
        SQLiteDatabase sqlite = getWritableDatabase();
        String where = "_id = ?";
        String[] args = {String.valueOf(oldId)};
        return sqlite.update("song", values, where, args);
    }

    public int deleteSongById(int id) {
        String where = "_id = ?";
        String[] args = {String.valueOf(id)};
        SQLiteDatabase sqlite = getWritableDatabase();
        return sqlite.delete("song", where, args);
    }

    public void getSongRecordFromCursor(Cursor cursor, List<Song> songList) {
        int id = cursor.getInt(0);
        String name = cursor.getString(1);
        String singer = cursor.getString(2);
        String album = cursor.getString(3);
        String type = cursor.getString(4);
        boolean love = cursor.getInt(5) != 0 ? true : false;
        songList.add(new Song(id, name, singer, album, type, love));
    }

    public void getSongValuesFromRecord(ContentValues values, Song song) {
        values.put("name", song.getName());
        values.put("singer", song.getSinger());
        values.put("album", song.getAlbum());
        values.put("type", song.getType());
        values.put("love", song.isLove() ? 1 : 0);
    }


}
