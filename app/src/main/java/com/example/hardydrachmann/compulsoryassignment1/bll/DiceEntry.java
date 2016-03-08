package com.example.hardydrachmann.compulsoryassignment1.bll;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class DiceEntry implements Parcelable {
    private int[] diceFaces;
    private int i = 0;

    public DiceEntry(int numberOfDices) {
        diceFaces = new int[numberOfDices];
    }

    // DiceEntry constructor
    public DiceEntry(int[] diceFaces) {
        this.diceFaces = diceFaces;
        i = diceFaces.length;
    }

    // Parcelable constructor
    protected DiceEntry(Parcel in) {
        diceFaces = in.createIntArray();
        i = in.readInt();
    }

    public int[] getDiceFaces() {
        return diceFaces;
    }

    public void setDiceFace(int diceFace) {
        if (i < diceFaces.length) {
            diceFaces[i++] = diceFace;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeIntArray(diceFaces);
    }

    public static final Creator<DiceEntry> CREATOR = new Creator<DiceEntry>() {
        @Override
        public DiceEntry createFromParcel(Parcel in) {
            Log.d("createFromParcel", " has been called");
            return new DiceEntry(in);
        }

        @Override
        public DiceEntry[] newArray(int size) {
            return new DiceEntry[size];
        }
    };
}