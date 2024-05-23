package com.example.songmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.songmanager.entity.Song;
import com.example.songmanager.helper.SongHelper;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner spAlbum, spType;
    private EditText eSongName, eSingerName;
    private Button btnAdd, btnCancel;
    private CheckBox ckLove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btnAdd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    private void initView() {
        spAlbum = findViewById(R.id.spAlbum);
        spType = findViewById(R.id.spType);
        eSongName = findViewById(R.id.eName);
        eSingerName = findViewById(R.id.eSingerName);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        spAlbum.setAdapter(new ArrayAdapter<>(this, R.layout.item_spinner,
                getResources().getStringArray(R.array.album)));
        spType.setAdapter(new ArrayAdapter<>(this, R.layout.item_spinner,
                getResources().getStringArray(R.array.type)));
        ckLove = findViewById(R.id.checkLove);
    }

    @Override
    public void onClick(View view) {
        if (view == btnAdd){
            String songName = eSongName.getText().toString();
            String album = spAlbum.getSelectedItem().toString();
            String type = spType.getSelectedItem().toString();
            boolean isLove = ckLove.isChecked();
            String singerName = eSingerName.getText().toString();
            if (!songName.isEmpty() && !singerName.isEmpty()){
                SongHelper songHelper = new SongHelper(this);
                Song newSong = new Song(songName, singerName, album, type, isLove);
                songHelper.addSong(newSong);
                finish();
            }
            else Toast.makeText(this, "Nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
        }
        if (view == btnCancel){
            finish();
        }
    }
}