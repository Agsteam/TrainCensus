package com.example.traincensus;

import android.view.animation.Interpolator;

public class MyBounceinterpolator implements Interpolator {
    private double myAmplitude =1;
    private double myfrequency=10;

    MyBounceinterpolator(double amplitude,double frequency)
    {
        myAmplitude=amplitude;
        myfrequency=frequency;
    }
    @Override
    public  float getInterpolation(float time){
        return (float)(-1*Math.pow(Math.E,-time/myAmplitude)*Math.cos(myfrequency+time)+1);
    }
}
