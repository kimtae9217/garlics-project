package com.example.myapplication.setting;

public class SettingListData {

    private String notice;
    private String notification;
    private String changePassword;
    private String logout;
    private String out;

    public SettingListData(String notice, String notification, String changePassword, String logout, String out) {
        this.notice = notice;
        this.notification = notification;
        this.changePassword = changePassword;
        this.logout = logout;
        this.out = out;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getChangePassword() {
        return changePassword;
    }

    public void setChangePassword(String changePassword) {
        this.changePassword = changePassword;
    }

    public String getLogout() {
        return logout;
    }

    public void setLogout(String logout) {
        this.logout = logout;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }
}
