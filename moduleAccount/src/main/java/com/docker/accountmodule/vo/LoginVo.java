package com.docker.accountmodule.vo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by zhangxindang on 2018/11/22.
 */

public class LoginVo implements Parcelable {


    private String username;
    private String password;
    private String icon;
    private int type;
    private List<Integer> collectIds;

    protected LoginVo(Parcel in) {
        username = in.readString();
        password = in.readString();
        icon = in.readString();
        type = in.readInt();
    }

    public static final Creator<LoginVo> CREATOR = new Creator<LoginVo>() {
        @Override
        public LoginVo createFromParcel(Parcel in) {
            return new LoginVo(in);
        }

        @Override
        public LoginVo[] newArray(int size) {
            return new LoginVo[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Integer> getCollectIds() {
        return collectIds;
    }

    public void setCollectIds(List<Integer> collectIds) {
        this.collectIds = collectIds;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(icon);
        dest.writeInt(type);
    }
}
