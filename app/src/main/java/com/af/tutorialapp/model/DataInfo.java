package com.af.tutorialapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DataInfo implements Parcelable {

    private int id;
    private String name;
    private String password;
    private String email;

    public DataInfo(){

    }

    public DataInfo(Parcel in) {
        id = in.readInt();
        name = in.readString();
        password = in.readString();
        email = in.readString();
    }

    public DataInfo(int id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static final Creator<DataInfo> CREATOR = new Creator<DataInfo>() {
        @Override
        public DataInfo createFromParcel(Parcel in) {
            return new DataInfo(in);
        }

        @Override
        public DataInfo[] newArray(int size) {
            return new DataInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(password);
        dest.writeString(email);
    }
}
