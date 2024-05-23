package com.example.songmanager;

import android.app.AlertDialog;
import android.content.Intent;
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

public class ModifyActivity extends AppCompatActivity implements View.OnClickListener  {
    private Spinner spAlbum, spType;
    private EditText eName, eSinger;
    private Button btnUpdate, btnDelete, btnCancel;
    private CheckBox ckLove;
    private Song song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        initView();
        btnCancel.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        Intent intent = getIntent();
        song = (Song) intent.getSerializableExtra("song");
        eName.setText(song.getName());
        eSinger.setText(song.getSinger());
        int position = 0;
        for (int i = 0; i < spAlbum.getCount(); i++){
            if (spAlbum.getItemAtPosition(i).toString().equals(song.getAlbum())){
                position = i;
                break;
            }
        }
        spAlbum.setSelection(position);
        for (int i = 0; i < spType.getCount(); i++){
            if (spType.getItemAtPosition(i).toString().equals(song.getType())){
                position = i;
                break;
            }
        }
        spType.setSelection(position);
    }

    private void initView(){
        spAlbum = findViewById(R.id.spAlbum);
        spType = findViewById(R.id.spType);
        eName = findViewById(R.id.eName);
        eSinger = findViewById(R.id.eSingerName);
        ckLove = findViewById(R.id.checkLove);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        spAlbum.setAdapter(new ArrayAdapter<>(this, R.layout.item_spinner,
                getResources().getStringArray(R.array.album)));
        spType.setAdapter(new ArrayAdapter<>(this, R.layout.item_spinner,
                getResources().getStringArray(R.array.type)));
    }

    @Override
    public void onClick(View view) {
        SongHelper songHelper = new SongHelper(this);
        if (view == btnCancel){
            finish();
        }
        if (view == btnUpdate){
            String name = eName.getText().toString();
            String singer = eSinger.getText().toString();
            String album = spAlbum.getSelectedItem().toString();
            String type = spType.getSelectedItem().toString();
            boolean isLove = ckLove.isChecked();
            if (!name.isEmpty() && !singer.isEmpty()){
                Song editSong = new Song(name, singer, album, type, isLove);
                songHelper.updateSong(editSong, song.getId());
                finish();
            }
            else Toast.makeText(this, "Nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
        }
        if (view == btnDelete){
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Xác nhận xóa");
            builder.setMessage("Bạn có chắc chắn muốn xóa bài hát " + song.getName() + " không ?");
            builder.setIcon(R.drawable.icon_delete);
            builder.setPositiveButton("Có", (dialogInterface, i) -> {
                songHelper.deleteSongById(song.getId());
                finish();
            });
            builder.setNegativeButton("Không", (dialogInterface, i) -> {
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
