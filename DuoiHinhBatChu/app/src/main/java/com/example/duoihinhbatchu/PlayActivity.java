package com.example.duoihinhbatchu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.duoihinhbatchu.adapter.DapAnAdapter;
import com.example.duoihinhbatchu.entity.Questions;
import com.example.duoihinhbatchu.model.QuestionsGame;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {
    private String resuft_ask = "";
    private ArrayList<String> arrAnwser;
    private GridView gdvAnwser;
    private ArrayList<String> arrSelect;
    private GridView gdvSelect;
    ServiceGame serviceGame = new ServiceGame();

    Questions questions;
    QuestionsGame questions_game;
    int id_question = 1;
    ImageView img_question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        init();
        showQuestion();
        setOnClick();

        Toast.makeText(getApplicationContext(), "main", Toast.LENGTH_SHORT).show();
    }
    private void init(){
        arrAnwser = new ArrayList<>();
        arrSelect = new ArrayList<>();
        gdvAnwser = findViewById(R.id.gdvAnwser);
        gdvSelect = findViewById(R.id.gdvSelect);
        img_question = findViewById(R.id.img_question);
        questions_game = new QuestionsGame(this);
    }


    private void createData(){
        arrAnwser.clear();
        arrSelect.clear();
//        megre resuft_ask
        ArrayList<String> megre_select_list = serviceGame.megreData(resuft_ask);
        int n = megre_select_list.size();
//        push data anwser and select
        for (int i = 0; i < n; i++) {
            arrAnwser.add("");
            arrSelect.add(megre_select_list.get(i).toUpperCase());
        }
    }

    private void setOnClick(){
        // get event anwser
        gdvAnwser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String text = (String) adapterView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), text + " " + String.valueOf(position), Toast.LENGTH_SHORT).show();
                int index_empty = serviceGame.findIndexEmpty(arrSelect);
                if(text.length()!=0 && index_empty >= 0){
                    arrAnwser.set(position, "");
                    arrSelect.set(index_empty, text);
                    showAnwser();
                    showSelect();
                }

            }
        });

        // get event select
        gdvSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String text = (String) adapterView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), text + " " + String.valueOf(position), Toast.LENGTH_SHORT).show();
                int index_empty = serviceGame.findIndexEmpty(arrAnwser);
                if(text.length()!=0 && index_empty >= 0){
                    arrAnwser.set(index_empty, text);
                    arrSelect.set(position, "");
                    showAnwser();
                    showSelect();
                }
                // check anwser of player
                if (serviceGame.findIndexEmpty(arrAnwser) == -1) {
                    // true
                    if(serviceGame.checkAnwser(arrAnwser, resuft_ask)){
                        id_question++;
                        showQuestion();
                        Toast.makeText(getApplicationContext(), "you won", Toast.LENGTH_SHORT).show();
                    }
                    // false
                    else{
                        Toast.makeText(getApplicationContext(), "not true", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        Toast.makeText(getApplicationContext(), "event", Toast.LENGTH_SHORT).show();
    }

    private void showQuestion(){
        questions = questions_game.getQuestion(id_question);
        resuft_ask = questions.getResult();
        Glide.with(this).load(questions.getImage()).into(img_question);
        createData();
        showAnwser();
        showSelect();
    }

    private void showAnwser(){
        gdvAnwser.setNumColumns(4);
        gdvAnwser.setAdapter(new DapAnAdapter(this, 0, arrAnwser));
    }

    private void showSelect(){
        gdvSelect.setNumColumns(4);
        gdvSelect.setAdapter(new DapAnAdapter(this, 0, arrSelect));
    }


    public void onClickHelp(View view) {
        String[] anwser = resuft_ask.trim().replace("  ", " ").split(" ");
        for(int i=0; i<arrAnwser.size(); i++){
            String word =  anwser[i].trim().toUpperCase();
            String word1 = arrAnwser.get(i);
            boolean indexSelect = true;
            if(!word1.equals(word)){
                arrAnwser.set(i, word);
                for(int j=0; j<arrSelect.size();j++){
                    if(arrSelect.get(j).equals(word)){
                        arrSelect.set(j, word1);
                        indexSelect = false;
                        break;
                    }
                }
                if(indexSelect){
                    for(int j=arrAnwser.size()-1; j>i;j--){
                        if(arrAnwser.get(j).equals(word)){
                            arrAnwser.set(j,"");
                            arrSelect.set(serviceGame.findIndexEmpty(arrSelect), word1);
                            break;
                        }
                    }
                }

                if(serviceGame.checkAnwser(arrAnwser, resuft_ask)){
                    id_question++;
                    showQuestion();
                    Toast.makeText(getApplicationContext(), "you won", Toast.LENGTH_SHORT).show();
                }
                else{
                    showAnwser();
                    showSelect();
                }
                return;
            }
        }

    }

    public void onCLickNext(View view) {
        id_question++;
        showQuestion();
        Toast.makeText(getApplicationContext(), "Next", Toast.LENGTH_SHORT).show();
    }
}