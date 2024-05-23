package com.example.view_pager_vs_tab_layout_2.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.view_pager_vs_tab_layout_2.MainActivity;
import com.example.view_pager_vs_tab_layout_2.R;
import com.example.view_pager_vs_tab_layout_2.adapter.PlayerAdapter;
import com.example.view_pager_vs_tab_layout_2.adapter.SpinnerAdapter;
import com.example.view_pager_vs_tab_layout_2.model.Player;
import com.example.view_pager_vs_tab_layout_2.model.PlayerItemListener;

public class FragmentAdd extends Fragment implements PlayerItemListener {
    private PlayerAdapter playerAdapter;
    private Spinner spinner;
    private EditText editName, editPrice, editInfo;
    private Button btnAdd, btnUpdate;
    private RecyclerView recyclerView;
    private int currentPosition;
    private int[] images = {R.drawable.player_1, R.drawable.player_2, R.drawable.player_3, R.drawable.player_4, R.drawable.player_5, R.drawable.player_6};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.initView(view);
        this.catchEvents();
    }

    private void catchEvents() {
        this.playerAdapter.setPlayerItemListener(this);

        this.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String i = spinner.getSelectedItem().toString();

                int image;
//                check valid
                try {
                    image = images[Integer.parseInt(i)];
                    double price = Double.parseDouble(editPrice.getText().toString());

                    Player player = new Player(image, editName.getText().toString(), price, editInfo.getText().toString());

                    playerAdapter.add(player);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "error! price is number", Toast.LENGTH_SHORT).show();
                    System.out.println(e);
                }
            }
        });

        this.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String i = spinner.getSelectedItem().toString();

                int image;

                try {
                    image = images[Integer.parseInt(i)];
                    double price = Double.parseDouble(editPrice.getText().toString());

                    Player player = new Player(image, editName.getText().toString(), price, editInfo.getText().toString());

                    playerAdapter.update(currentPosition, player);

                    btnAdd.setEnabled(true);
                    btnUpdate.setEnabled(false);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "error! price is number", Toast.LENGTH_SHORT).show();
                    System.out.println(e);
                }
            }
        });

    }

    private void initView(View view) {
        this.spinner = view.findViewById(R.id.spinner);

        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(getContext(), this.images);
        this.spinner.setAdapter(spinnerAdapter);

        this.editName = view.findViewById(R.id.editName);
        this.editPrice = view.findViewById(R.id.editPrice);
        this.editInfo = view.findViewById(R.id.editDesc);
        this.btnAdd = view.findViewById(R.id.addBtn);
        this.btnUpdate = view.findViewById(R.id.updateBtn);
        this.recyclerView = view.findViewById(R.id.rcView);

        this.btnUpdate.setEnabled(false);

        this.playerAdapter = new PlayerAdapter((MainActivity) getActivity());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
//        set layout
        this.recyclerView.setLayoutManager(linearLayoutManager);
//        set data
        this.recyclerView.setAdapter(this.playerAdapter);
    }

    @Override
    public void onPlayerItemClick(View view, int position) {
        this.btnAdd.setEnabled(false);
        this.btnUpdate.setEnabled(true);

        this.currentPosition = position;

        Player selectedPlayer = this.playerAdapter.getItem(position);

        int playerAvatar = selectedPlayer.getAvatar();

        int currentSpinnerPosition = 0;
        for (int i = 0; i < this.images.length; i++) {
            if (playerAvatar == images[i]) {
                currentSpinnerPosition = i;
                break;
            }
        }

        this.spinner.setSelection(currentSpinnerPosition);
        this.editName.setText(selectedPlayer.getName());
        this.editPrice.setText(selectedPlayer.getPrice() + "");
        this.editInfo.setText(selectedPlayer.getDescription());
    }
}
