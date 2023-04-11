package com.example.quizassigment;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

import com.google.android.material.imageview.ShapeableImageView;

public class User implements Parcelable {

    String name;
    int score;
    int BestScore = 0;

    protected User(Parcel in) {
        name = in.readString();
        score = in.readInt();
        BestScore = in.readInt();
    }
    public User(){

    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(score);
        parcel.writeInt(BestScore);
    }
}
