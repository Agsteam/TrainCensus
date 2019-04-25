package com.example.traincensus;



public class Adddata1
{
    private String  div,station,train,dudate,coachnum,coachtype;
    private int cc,tc,av;
    public Adddata1(){}

    public Adddata1(String div,String sta1,String dudate,String train,int cc,int tc,int av,String coachnum,String coachtype)
    {
        this.div=div;
        this.station=sta1;
        this.dudate=dudate;
        this.train=train;
        this.cc=cc;
        this.tc=tc;
        this.av=av;
        this.coachnum=coachnum;
        this.coachtype=coachtype;
    }

    public String getDiv() {
        return div;
    }
    public String getStation() {
        return station;
    }
    public String getTrain() {
        return train;
    }
    public int getCc() {
        return cc;
    }
    public int getTc() {
        return tc;
    }
    public int getAv() {
        return av;
    }
    public String getCoachnum() {
        return coachnum;
    }
    public String getDudate() {
        return dudate;
    }
    public String getCoachtype(){return coachtype;}
}