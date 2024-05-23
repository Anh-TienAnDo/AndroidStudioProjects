package com.example.view_pager_vs_tab_layout_2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.view_pager_vs_tab_layout_2.MainActivity;
import com.example.view_pager_vs_tab_layout_2.R;
import com.example.view_pager_vs_tab_layout_2.adapter.SearchAdapter;
import com.example.view_pager_vs_tab_layout_2.model.Player;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearch extends Fragment {
    private SearchAdapter searchAdapter;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private List<Player> players;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.searchView = view.findViewById(R.id.search);
        this.recyclerView = view.findViewById(R.id.rcViewSearch);
        this.searchAdapter = new SearchAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setAdapter(this.searchAdapter);

        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                this.filter(s);
                return false;
            }

            private void filter(String s) {
                List<Player> filteredPlayers = new ArrayList<>();

                for (Player player : players) {
                    if (player.getName().toLowerCase().contains(s.toLowerCase())) {
                        filteredPlayers.add(player);
                    }
                }

                if (filteredPlayers.isEmpty()) {
                    Toast.makeText(getContext(), "Not found", Toast.LENGTH_LONG);
                } else {
                    searchAdapter.setPlayers(filteredPlayers);
                }
            }

        });
    }

    @Override
    public void onResume() {
        super.onResume();

        this.players = ((MainActivity) getActivity()).players;
    }
}
