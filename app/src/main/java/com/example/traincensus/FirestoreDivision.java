package com.example.traincensus;
public class FirestoreDivision
{
    private String Name,Pfno,Station,Shiftin,Shiftout,DOB,Accestype,Division,Section;
    private String Date1;

    public FirestoreDivision()
    {
    }

    public FirestoreDivision(String Division,String Name,String Pfno,String Station,String Shiftin,String Shiftout,String DOB,String Accestype,String Section,String Date1)
    {
        this.Division=Division;
        this.Name=Name;
        this.Pfno=Pfno;
        this.Station=Station;
        this.Shiftin=Shiftin;
        this.Shiftout=Shiftout;
        this.DOB=DOB;
        this.Accestype=Accestype;
        this.Section=Section;
        this.Date1=Date1;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPfno(String pfno) {
        Pfno = pfno;
    }

    public void setStation(String station) {
        Station = station;
    }

    public void setShiftin(String shiftin) {
        Shiftin = shiftin;
    }

    public void setShiftout(String shiftout) {
        Shiftout = shiftout;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setAccestype(String accestype) {
        Accestype = accestype;
    }

    public void setDivision(String division) {
        Division = division;
    }

    public void setSection(String section) {
        Section = section;
    }

    public void setDate1(String date1) {
        Date1 = date1;
    }

    public String getName()
    {
        return Name;
    }

    public String getPfno()
    {
        return Pfno;
    }

    public String getStation()
    {
        return Station;
    }

    public String getShiftin()
    {
        return Shiftin;
    }

    public String getShiftout()
    {
        return Shiftout;
    }

    public String getDOB()
    {
        return DOB;
    }

    public String getAccestype()
    {
        return Accestype;
    }

    public String getDivision()
    {
        return Division;
    }

    public String getSection()
    {
        return Section;
    }

    public String getDate1()
    {
        return Date1;
    }
}