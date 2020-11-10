package com.example.healthyhabits;

import android.os.Parcel;
import android.os.Parcelable;

public class EmergencyObject implements Parcelable{


    private String mfechaIni;
    private String descr;
    private String mtitulo;
    private String mHourIni;
    private String mPulse;


    public EmergencyObject(String mfechaIni, String descr, String mtitulo, String mHourIni, String mPulse) {
        this.mfechaIni = mfechaIni;
        this.descr = descr;
        this.mtitulo = mtitulo;
        this.mHourIni = mHourIni;
        this.mPulse = mPulse;
    }

    public EmergencyObject() {
    }

    protected EmergencyObject(Parcel in) {
        mfechaIni = in.readString();
        descr = in.readString();
        mtitulo = in.readString();
        mHourIni = in.readString();
        mPulse = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mfechaIni);
        dest.writeString(descr);
        dest.writeString(mtitulo);
        dest.writeString(mHourIni);
        dest.writeString(mPulse);
    }

    @SuppressWarnings("unused")
    public static final Creator<EmergencyObject> CREATOR = new Creator<EmergencyObject>() {
        @Override
        public EmergencyObject createFromParcel(Parcel in) {
            return new EmergencyObject(in);
        }

        @Override
        public EmergencyObject[] newArray(int size) {
            return new EmergencyObject[size];
        }
    };


    public String getMfechaIni() {
        return mfechaIni;
    }

    public void setMfechaIni(String mfechaIni) {
        this.mfechaIni = mfechaIni;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getMtitulo() {
        return mtitulo;
    }

    public void setMtitulo(String mtitulo) {
        this.mtitulo = mtitulo;
    }

    public String getmHourIni() {
        return mHourIni;
    }

    public void setmHourIni(String mHourIni) {
        this.mHourIni = mHourIni;
    }

    public String getmPulse() {
        return mPulse;
    }

    public void setmPulse(String mPulse) {
        this.mPulse = mPulse;
    }

    public static Creator<EmergencyObject> getCREATOR() {
        return CREATOR;
    }
}
