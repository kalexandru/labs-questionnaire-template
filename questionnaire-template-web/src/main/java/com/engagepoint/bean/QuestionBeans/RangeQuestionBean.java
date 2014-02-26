package com.engagepoint.bean.QuestionBeans;


import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Created by stanislav.sobolev on 2/17/14.
 */
public class RangeQuestionBean extends QuestionBean {

    private String minValue="0";
    private String maxValue="0";

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

}