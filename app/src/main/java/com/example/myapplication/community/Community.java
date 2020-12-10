package com.example.myapplication.community;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.PreferenceManager;
import com.example.myapplication.R;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class Community extends Fragment{
    final static int CODE = 1;
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

                Intent intent = new Intent(getActivity(),EnrollGroup.class);
                startActivityForResult(intent,CODE);

            }
        });

        return viewGroup;
    }

    //사용자에게 입력받은 값으로 리사이클뷰의 값 변경
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       switch (requestCode) {
           case CODE:
                   CommunityGroupListData communityGroupListData = new CommunityGroupListData(R.drawable.profile_profile,"그룹 이름", "그룹 인원","그룹 내용");
                   communityGroupListData.setGroup_list_name(data.getStringExtra("groupName"));
                   communityGroupListData.setGroup_list_content(data.getStringExtra("groupContents"));
                   communityGroupListData.setGroup_list_numbers(data.getStringExtra("groupNumbers"));
                   PreferenceManager.setString(getContext(),"rebuild",data.getStringExtra("groupName"));
                   PreferenceManager.setString(getContext(),"number",data.getStringExtra("groupNumbers"));
                   arrayList.add(communityGroupListData);
                   recycleAdapter.notifyDataSetChanged();

       }
    }
}

