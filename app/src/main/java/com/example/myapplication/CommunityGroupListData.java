package com.example.myapplication;

public class CommunityGroupListData {

    private int group_list_profile;
    private String group_list_name;
    private String group_list_content;

    public CommunityGroupListData(int group_list_profile, String group_list_name, String group_list_content) {
        this.group_list_profile = group_list_profile;
        this.group_list_name = group_list_name;
        this.group_list_content = group_list_content;
    }

    public int getGroup_list_profile() {
        return group_list_profile;
    }

    public void setGroup_list_profile(int group_list_profile) {
        this.group_list_profile = group_list_profile;
    }

    public String getGroup_list_name() {
        return group_list_name;
    }

    public void setGroup_list_name(String group_list_name) {
        this.group_list_name = group_list_name;
    }

    public String getGroup_list_content() {
        return group_list_content;
    }

    public void setGroup_list_content(String group_list_content) {
        this.group_list_content = group_list_content;
    }
}
