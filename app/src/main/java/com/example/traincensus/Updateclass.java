package com.example.traincensus;

public class Updateclass {

    private String name,pf,mobile;
    private int id;

    public Updateclass() {
        super();
    }

    public Updateclass(int id,String name,String pf,String mobile) {
        super();
        this.id=id;
        this.name=name;
        this.pf=pf;
        this.mobile=mobile;
    }
    public int getId(){return  id;}
    public String getName() {
        return name;
    }

    public String getPf() {
        return pf;
    }

    public String getMobile() {
        return mobile;
    }
    public void setId(int id ){
        this.id=id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}

