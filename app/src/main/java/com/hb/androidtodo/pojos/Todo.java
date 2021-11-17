package com.hb.androidtodo.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Todo implements Parcelable {
    private Long id;
    private String name;
    private String urgency;

    public Todo() {

    }

    public Todo(String name, String urgency) {
        this.name = name;
        this.urgency = urgency;
    }

    public Todo (Parcel parcel){
        this.id = parcel.readLong();
        this.name = parcel.readString();
        this.urgency = parcel.readString();
    }

    public static final Creator<Todo> CREATOR = new Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel in) {
            return new Todo(in);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(id);
            dest.writeString(name);
            dest.writeString(urgency);
    }
}
