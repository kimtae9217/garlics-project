package com.example.myapplication.community;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

//리사이클뷰를 사용하기 위한 코드, 기능들
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.CustomViewHolder> {

    private Context context;

    private ArrayList<CommunityGroupListData> arrayList;

    public RecycleAdapter( ArrayList<CommunityGroupListData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecycleAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.community_group_list,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        context = parent.getContext();


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.CustomViewHolder holder, int position) {
        holder.group_list_profile.setImageResource(arrayList.get(position).getGroup_list_profile());
        holder.group_list_name.setText(arrayList.get(position).getGroup_list_name());
        holder.group_list_content.setText(arrayList.get(position).getGroup_list_content());
        holder.group_list_numbers.setText(arrayList.get(position).getGroup_list_numbers());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CommunityGroup.class);
                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList? arrayList.size():0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView group_list_profile;
        protected TextView group_list_name;
        protected TextView group_list_content;
        protected TextView group_list_numbers;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.group_list_profile = (ImageView)itemView.findViewById(R.id.group_list_profile);
            this.group_list_name = (TextView)itemView.findViewById(R.id.group_list_name);
            this.group_list_content = (TextView)itemView.findViewById(R.id.group_list_content);
            this.group_list_numbers = (TextView)itemView.findViewById(R.id.group_list_numbers);

        }
    }
}
