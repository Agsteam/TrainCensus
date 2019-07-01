package com.example.traincensus.Model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Train implements Serializable, Parcelable
{

    @SerializedName("actdep")
    @Expose
    private String actdep;
    @SerializedName("actarr")
    @Expose
    private String actarr;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("scharr")
    @Expose
    private String scharr;
    @SerializedName("delayarr")
    @Expose
    private String delayarr;
    @SerializedName("schdep")
    @Expose
    private String schdep;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("delaydep")
    @Expose
    private String delaydep;
    public final static Parcelable.Creator<Train> CREATOR = new Creator<Train>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Train createFromParcel(Parcel in) {
            return new Train(in);
        }

        public Train[] newArray(int size) {
            return (new Train[size]);
        }

    }
            ;
    private final static long serialVersionUID = 1444704869916223659L;

    protected Train(Parcel in) {
        this.actdep = ((String) in.readValue((String.class.getClassLoader())));
        this.actarr = ((String) in.readValue((String.class.getClassLoader())));
        this.number = ((String) in.readValue((String.class.getClassLoader())));
        this.scharr = ((String) in.readValue((String.class.getClassLoader())));
        this.delayarr = ((String) in.readValue((String.class.getClassLoader())));
        this.schdep = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.delaydep = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Train() {
    }

    public String getActdep() {
        return actdep;
    }

    public void setActdep(String actdep) {
        this.actdep = actdep;
    }

    public String getActarr() {
        return actarr;
    }

    public void setActarr(String actarr) {
        this.actarr = actarr;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getScharr() {
        return scharr;
    }

    public void setScharr(String scharr) {
        this.scharr = scharr;
    }

    public String getDelayarr() {
        return delayarr;
    }

    public void setDelayarr(String delayarr) {
        this.delayarr = delayarr;
    }

    public String getSchdep() {
        return schdep;
    }

    public void setSchdep(String schdep) {
        this.schdep = schdep;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDelaydep() {
        return delaydep;
    }

    public void setDelaydep(String delaydep) {
        this.delaydep = delaydep;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(actdep);
        dest.writeValue(actarr);
        dest.writeValue(number);
        dest.writeValue(scharr);
        dest.writeValue(delayarr);
        dest.writeValue(schdep);
        dest.writeValue(name);
        dest.writeValue(delaydep);
    }

    public int describeContents() {
        return 0;
    }

}

