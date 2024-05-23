package com.example.track_tablayout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener{

    private Spinner spAlbum, spType;
    private EditText eTrackName, eSingerName;
    private RatingBar cbIsFavourite;
    private Button btnUpdate, btnRemove, btnBack;
    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        this.initView();
        this.catchEvent();
        this.handleData();
    }

    private void handleData() {
        Intent intent = getIntent();

        this.item = (Item) intent.getSerializableExtra("item");

        this.eTrackName.setText(this.item.getName());
        this.eSingerName.setText(this.item.getSinger());
        if(this.item.isFavourite()){
            this.cbIsFavourite.setRating(1);
        }else{
            this.cbIsFavourite.setRating(0);
        }

        int currentPositionSpinnerAlbum = 0;
        for (int i = 0; i < this.spAlbum.getCount(); i++) {
            if (this.spAlbum.getItemAtPosition(i).toString().equalsIgnoreCase(this.item.getAlbum())) {
                currentPositionSpinnerAlbum = i;
                break;
            }
        }
        this.spAlbum.setSelection(currentPositionSpinnerAlbum);

        int currentPositionSpinnerType = 0;
        for (int i = 0; i < this.spType.getCount(); i++) {
            if (this.spType.getItemAtPosition(i).toString().equalsIgnoreCase(this.item.getType())) {
                currentPositionSpinnerType = i;
                break;
            }
        }
        this.spType.setSelection(currentPositionSpinnerType);
    }

    private void catchEvent() {
        this.btnUpdate.setOnClickListener(this);
        this.btnRemove.setOnClickListener(this);
        this.btnBack.setOnClickListener(this);
    }

    private void initView() {
        this.spAlbum = findViewById(R.id.spAlbumId);
        this.spType = findViewById(R.id.spTypeId);
        this.eTrackName = findViewById(R.id.eTrackNameId);
        this.eSingerName = findViewById(R.id.eSingerNameId);
        this.cbIsFavourite = findViewById(R.id.cbIsFavouriteId);
        this.btnUpdate = findViewById(R.id.btnUpdateId);
        this.btnRemove = findViewById(R.id.btnRemoveId);
        this.btnBack = findViewById(R.id.btnBackId);

        this.spAlbum.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.album)));
        this.spType.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.type)));
    }


    @Override
    public void onClick(View view) {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);

        if (view == this.btnBack) {
            finish();
            return;
        }

        if (view == this.btnUpdate) {
            String name = this.eTrackName.getText().toString();
            String singer = this.eSingerName.getText().toString();
            String album = this.spAlbum.getSelectedItem().toString();
            String type = this.spType.getSelectedItem().toString();
            boolean isFavourite = false;
            if(this.cbIsFavourite.getRating()==1){
                isFavourite = true;
            }

            if (!name.isEmpty() && !singer.isEmpty() && !album.isEmpty() && !type.isEmpty()) {
                int id = this.item.getId();
                Item updatedItem = new Item(id, name, singer, album, type, isFavourite);

                sqLiteHelper.update(updatedItem);
                finish();
            }
        }

        if (view == this.btnRemove) {
            int id = this.item.getId();

            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

            builder.setTitle("Delete nofitication");
            builder.setMessage("Do you want to delete: " + this.item.getName());
            builder.setIcon(R.drawable.remove);

            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    sqLiteHelper.delete(id);
                    finish();
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
}