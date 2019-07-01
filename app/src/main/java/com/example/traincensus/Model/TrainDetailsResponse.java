package com.example.traincensus.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TrainDetailsResponse implements Serializable, Parcelable
{

    @SerializedName("trains")
    @Expose
    private List<Train> trains = new ArrayList<Train>();
    @SerializedName("debit")
    @Expose
    private Integer debit;
    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("total")
    @Expose
    private Integer total;
    public final static Creator<TrainDetailsResponse> CREATOR = new Creator<TrainDetailsResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TrainDetailsResponse createFromParcel(Parcel in) {
            return new TrainDetailsResponse(in);
        }

        public TrainDetailsResponse[] newArray(int size) {
            return (new TrainDetailsResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = 1107645016216514931L;

    protected TrainDetailsResponse(Parcel in) {
        in.readList(this.trains, (Train.class.getClassLoader()));
        this.debit = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.responseCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.total = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public TrainDetailsResponse() {
    }

    public List<Train> getTrains() {
        return trains;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    public Integer getDebit() {
        return debit;
    }

    public void setDebit(Integer debit) {
        this.debit = debit;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(trains);
        dest.writeValue(debit);
        dest.writeValue(responseCode);
        dest.writeValue(total);
    }

    public int describeContents() {
        return 0;
    }

}

