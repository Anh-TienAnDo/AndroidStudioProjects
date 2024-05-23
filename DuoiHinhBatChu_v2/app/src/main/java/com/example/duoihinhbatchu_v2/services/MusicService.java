package com.example.duoihinhbatchu_v2.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;

import com.example.duoihinhbatchu_v2.R;

public class MusicService extends Service {
    private MediaPlayer mediaPlayer;
    public MusicService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Khởi tạo MediaPlayer
//        mediaPlayer = MediaPlayer.create(this,
//                Uri.parse("D:\\music\\song.mp3"));
        mediaPlayer = MediaPlayer.create(this, R.raw.song1);
        // Thiết lập lặp lại bài hát
        mediaPlayer.setLooping(true);
        // Bắt đầu phát nhạc
        mediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}