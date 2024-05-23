package com.example.duoihinhbatchu_v2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duoihinhbatchu_v2.R;
import com.example.duoihinhbatchu_v2.adapters.ResultRiddleAdapter;
import com.example.duoihinhbatchu_v2.dao.SQLiteHelper;
import com.example.duoihinhbatchu_v2.models.Riddle;
import com.example.duoihinhbatchu_v2.models.RiddlesGame;
import com.example.duoihinhbatchu_v2.services.ServiceGame;

import java.util.ArrayList;
import java.util.List;

public class PlayFragment extends Fragment {
    private String resuft_riddle = "";
    private Riddle riddle;
    private List<String> arrAnwser;
    private GridView gdvAnwser;
    private List<String> arrSelect;
    private GridView gdvSelect;
    private ServiceGame serviceGame = new ServiceGame();
    private RiddlesGame riddlesGame;
    private int riddle_id = 0;
    private ImageView imgRiddleId;
    Button btNextId, btHelpId, btRestartId, btBackId;
    private SQLiteHelper sqLiteHelper;
    private int[] images = {R.drawable.vong_tuan_hoan, R.drawable.baovemoitruong_susongtruongton,
            R.drawable.langphi_ngheodoi, R.drawable.nonggian_nhansac,
            R.drawable.gieonhangatqua, R.drawable.lamnhieudieutot_duocnhieunguoiquy};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.play_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.initView(view);
        this.showRiddle();
        this.catchEvent();
    }

    private void initView(View view){
        this.arrAnwser = new ArrayList<>();
        this.arrSelect = new ArrayList<>();
        this.gdvAnwser = view.findViewById(R.id.gdvAnwserId);
        this.gdvSelect = view.findViewById(R.id.gdvSelectId);
        this.imgRiddleId = view.findViewById(R.id.imgRiddleId);
        this.btNextId = view.findViewById(R.id.btNextId);
        this.btHelpId = view.findViewById(R.id.btHelpId);
        this.btBackId = view.findViewById(R.id.btBackId);
        this.btRestartId = view.findViewById(R.id.btRestartId);
        this.sqLiteHelper = new SQLiteHelper(getActivity());
        riddlesGame = new RiddlesGame(this.sqLiteHelper.getAll());
    }
    private void showRiddle(){
        this.riddle = riddlesGame.getRiddle(this.riddle_id);
        this.resuft_riddle = this.riddle.getResult();
        for (int i = 0; i < this.images.length; i++) {
            if(i==this.riddle.getImage()){
                imgRiddleId.setImageResource(this.images[i]);
            }
        }
        createData();
        showAnwser();
        showSelect();
    }

    private void createData(){
        arrAnwser.clear();
        arrSelect.clear();
        ArrayList<String> megre_select_list = serviceGame.megreData(resuft_riddle);
        int n = megre_select_list.size();
        for (int i = 0; i < n; i++) {
            arrAnwser.add("");
            arrSelect.add(megre_select_list.get(i).toUpperCase());
        }
    }
    private void showAnwser(){
        gdvAnwser.setNumColumns(4);
        gdvAnwser.setAdapter(new ResultRiddleAdapter(getActivity(), 0, arrAnwser));
    }

    private void showSelect(){
        gdvSelect.setNumColumns(4);
        gdvSelect.setAdapter(new ResultRiddleAdapter(getActivity(), 0, arrSelect));
    }
    private void catchEvent() {
        this.btNextId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                riddle_id++;
                showRiddle();
                Toast.makeText(getActivity(), "Riddle id: " + riddle_id, Toast.LENGTH_SHORT).show();
            }
        });
        this.btBackId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                riddle_id--;
                showRiddle();
                Toast.makeText(getActivity(), "Riddle id: " + riddle_id, Toast.LENGTH_SHORT).show();
            }
        });
        this.btRestartId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                riddle_id=0;
                showRiddle();
                Toast.makeText(getActivity(), "Riddle id: " + riddle_id, Toast.LENGTH_SHORT).show();
            }
        });
        this.btHelpId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] anwser = resuft_riddle.trim().replace("  ", " ").split(" ");
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
                                    arrSelect.set(serviceGame.findIndexEmpty((ArrayList<String>) arrSelect), word1);
                                    break;
                                }
                            }
                        }

                        if(serviceGame.checkAnwser((ArrayList<String>) arrAnwser, resuft_riddle)){
                            riddle_id++;
                            showRiddle();
                            Toast.makeText(getActivity(), "you won", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            showAnwser();
                            showSelect();
                        }
                        return;
                    }
                }
            }
        });
        // get event undo anwser,
        gdvAnwser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String text = (String) adapterView.getItemAtPosition(position);
                Toast.makeText(getActivity(), text + " " + String.valueOf(position), Toast.LENGTH_SHORT).show();
                int index = serviceGame.findIndexEmpty((ArrayList<String>) arrSelect);
                if(text.length()>0 && index >= 0){
                    arrAnwser.set(position, "");
                    arrSelect.set(index, text);
                    showAnwser();
                    showSelect();
                }

            }
        });

        // get event click select
        gdvSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String text = (String) adapterView.getItemAtPosition(position);
                Toast.makeText(getActivity(), text + " " + String.valueOf(position), Toast.LENGTH_SHORT).show();
                int index = serviceGame.findIndexEmpty((ArrayList<String>) arrAnwser);
                if(text.length()>0 && index >= 0){
                    arrAnwser.set(index, text);
                    arrSelect.set(position, "");
                    showAnwser();
                    showSelect();
                }
                // check anwser of player
                if (serviceGame.findIndexEmpty((ArrayList<String>) arrAnwser) == -1) {
                    // true
                    if(serviceGame.checkAnwser((ArrayList<String>) arrAnwser, resuft_riddle)){
                        riddle_id++;
                        showRiddle();
                        Toast.makeText(getActivity(), "You won", Toast.LENGTH_SHORT).show();
                    }
                    // false
                    else{
                        Toast.makeText(getActivity(), "Not true", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        Toast.makeText(getActivity(), "Play Event", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onResume() {
        super.onResume();
        this.reRender();
    }
    private void reRender() {
        this.riddlesGame = new RiddlesGame(this.sqLiteHelper.getAll());
        showRiddle();
    }
}
