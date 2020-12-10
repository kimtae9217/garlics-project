package com.example.myapplication.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.PreferenceManager;
import com.example.myapplication.R;
import com.example.myapplication.community.CommunityGroupListData;
import com.example.myapplication.community.RecycleAdapter;

import java.util.ArrayList;

public class Setting extends Fragment{
    ViewGroup viewGroup;
    private ArrayList<SettingListData> arrayList;
    private SettingRecycleAdapter recycleAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.setting,container,false);
        recyclerView= (RecyclerView) viewGroup.findViewById(R.id.setting_rv);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();
        recycleAdapter = new SettingRecycleAdapter(arrayList);
        recyclerView.setAdapter(recycleAdapter);

        SettingListData settingListData = new SettingListData("공지사항","알림설정","비밀번호 변경","로그아웃","회원탈퇴");
        settingListData.setNotice(PreferenceManager.getString(getContext(),"rebuild"));
        arrayList.add(settingListData);
        recycleAdapter.notifyDataSetChanged();
        return viewGroup;
    }
}

