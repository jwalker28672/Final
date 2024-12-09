package com.example.cis183_final_jacobwalker;

public class RequestOrder
{
    int orderNum;
    int techNum;
    float hours;
    int typeId;
    String date;

    public RequestOrder()
    {

    }


    //=================================================================================
    //                             GETTERS
    //=================================================================================

    public int getOrderNum() {
        return orderNum;
    }

    public int getTechNum() {
        return techNum;
    }

    public float getHours() {
        return hours;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getDate() {
        return date;
    }

    //=================================================================================
    //                             SETTERS
    //=================================================================================


    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public void setTechNum(int techNum) {
        this.techNum = techNum;
    }

    public void setHours(float hours) {
        this.hours = hours;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
