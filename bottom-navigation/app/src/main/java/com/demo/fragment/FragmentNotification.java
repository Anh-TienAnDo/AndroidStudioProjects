package com.demo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.R;
import com.demo.adapter.MessageAdapter;
import com.demo.model.Message;

import java.util.ArrayList;
import java.util.List;

public class FragmentNotification extends Fragment {
    MessageAdapter messageAdapter;
    RecyclerView recyclerView;
    List<Message> list;

    public FragmentNotification() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noti, container, false);

        list = new ArrayList<>();
        list.add(new Message(R.drawable.ic_notifications, "Thanh", "Em an com chua?", "16:20"));
        list.add(new Message(R.drawable.ic_notifications, "Thanh1", "Em an com chua1?", "16:21"));
        list.add(new Message(R.drawable.ic_notifications, "Thanh2", "Em an com chua2?", "16:22"));
        list.add(new Message(R.drawable.ic_notifications, "Thanh3", "Em an com chua3?", "16:23"));
        list.add(new Message(R.drawable.ic_notifications, "Thanh4", "Em an com chua4?", "16:24"));

        recyclerView = view.findViewById(R.id.recycleView);
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);

        messageAdapter  = new MessageAdapter(view.getContext(),list);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(messageAdapter);

        return view;
    }
}
