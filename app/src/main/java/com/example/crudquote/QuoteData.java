package com.example.crudquote;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class QuoteData implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    String q;
    String a;

    public void setId(int id) {
        this.id = id;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public void setA(String a) {
        this.a = a;
    }

    public QuoteData(String q, String a) {
        this.q = q;
        this.a = a;
    }

    public QuoteData() {
    }

    protected QuoteData(Parcel in) {
        id = in.readInt();
        q = in.readString();
        a = in.readString();
    }

    public static final Creator<QuoteData> CREATOR = new Creator<QuoteData>() {
        @Override
        public QuoteData createFromParcel(Parcel in) {
            return new QuoteData(in);
        }

        @Override
        public QuoteData[] newArray(int size) {
            return new QuoteData[size];
        }
    };

    public String getQ() { return q; }

    public String getA() { return a; }

//    protected QuoteData() {
//        q = in.readString();
//        a = in.readString();
//    }


//    public static final Creator<QuoteData> CREATOR = new Creator<QuoteData>() {
//            @Override
//            public QuoteData createFromParcel(Parcel in) {
//                return new QuoteData(in);
//            }
//
//            @Override
//            public QuoteData[] newArray(int size) {
//                return new QuoteData[size];
//            }
//        };



        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(q);
            dest.writeString(a);
        }



}

