package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Community extends Fragment{
    private ArrayList<CommunityGroupListData> arrayList;
    private RecycleAdapter recycleAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    ViewGroup viewGroup;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.community,container,false);
        recyclerView= (RecyclerView) viewGroup.findViewById(R.id.grouop_list_rv);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();
        recycleAdapter = new RecycleAdapter(arrayList);
        recyclerView.setAdapter(recycleAdapter);


        Button btn_add = (Button)viewGroup.findViewById(R.id.group_make_btn);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommunityGroupListData communityGroupListData = new CommunityGroupListData(R.drawable.maneul2,"그룸 이름","그룹 내용");
                arrayList.add(communityGroupListData);
                recycleAdapter.notifyDataSetChanged();
            }
        });

        return viewGroup;
    }
}

