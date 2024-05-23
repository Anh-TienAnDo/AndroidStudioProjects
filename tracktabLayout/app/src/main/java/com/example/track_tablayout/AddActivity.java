package com.example.track_tablayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.example.track_tablayout.dao.SQLiteHelper;
import com.example.track_tablayout.model.Item;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    private Spinner spAlbum, spType;
    private EditText eTrackName, eSingerName;
    private RatingBar cbIsFavourite;
    private Button btnAdd, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        this.initView();
        this.catchEvent();
    }

    private void catchEvent() {
        this.btnAdd.setOnClickListener(this);
        this.btnCancel.setOnClickListener(this);
    }

    private void initView() {
        this.spAlbum = findViewById(R.id.spAlbumId);
        this.spType = findViewById(R.id.spTypeId);
        this.eTrackName = findViewById(R.id.eTrackNameId);
        this.eSingerName = findViewById(R.id.eSingerNameId);
        this.cbIsFavourite = findViewById(R.id.cbIsFavouriteId);
        this.btnAdd = findViewById(R.id.btnAddId);
        this.btnCancel = findViewById(R.id.btnCancelId);

        this.spAlbum.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.album)));
        this.spType.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.type)));
    }

    @Override
    public void onClick(View view) {
        if (view == this.btnCancel) {
            finish();
            return;
        }

        if (view == this.btnAdd) {
            String trackName = this.eTrackName.getText().toString();
            String singerName = this.eSingerName.getText().toString();
            String album = this.spAlbum.getSelectedItem().toString();
            String type = this.spType.getSelectedItem().toString();
            boolean isFavourite = false;
            if(this.cbIsFavourite.getRating()==1){
                isFavourite = true;
            }

            if (!trackName.isEmpty() && !singerName.isEmpty() && !album.isEmpty() && !type.isEmpty()) {
                Item item = new Item(trackName, singerName, album, type, isFavourite);

                SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
                sqLiteHelper.addItem(item);
                finish();
            }
        }
    }
}