package com.example.myapplication.setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.community.CommunityGroupListData;
import com.example.myapplication.community.RecycleAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SettingRecycleAdapter extends RecyclerView.Adapter<SettingRecycleAdapter.CustomViewHolder> {

    private Context context;

    private ArrayList<SettingListData> arrayList;

    public SettingRecycleAdapter(ArrayList<SettingListData> arrayList) {
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public SettingRecycleAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.setting_list,parent,false);
        SettingRecycleAdapter.CustomViewHolder holder = new SettingRecycleAdapter.CustomViewHolder(view);
        context = parent.getContext();

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.notice.setText(arrayList.get(position).getNotice());
        holder.notification.setText(arrayList.get(position).getNotification());
        holder.changePassword.setText(arrayList.get(position).getChangePassword());
        holder.logout.setText(arrayList.get(position).getLogout());
        holder.out.setText(arrayList.get(position).getOut());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




    }

    @Override
    public int getItemCount() {
        return (null != arrayList? arrayList.size():0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView notice;
        protected TextView notification;
        protected TextView changePassword;
        protected TextView logout;
        protected TextView out;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.notice = (TextView)itemView.findViewById(R.id.setting_notice);
            this.notification = (TextView)itemView.findViewById(R.id.setting_notification);
            this.changePassword = (TextView)itemView.findViewById(R.id.setting_changePassword);
            this.logout = (TextView)itemView.findViewById(R.id.setting_logout);
            this.out = (TextView)itemView.findViewById(R.id.setting_out);
        }
    }
}
