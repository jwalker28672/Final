package com.example.cis183_final_jacobwalker;

public class Tech
{
    private int techNum;
    private String fName;
    private String lName;
    private String uName;
    private String password;

    public Tech()
    {

    }


    //==================================================================================
    //                                   GETTERS
    //==================================================================================


    public int getTechNum() {
        return techNum;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getuName() {
        return uName;
    }

    public String getPassword() {
        return password;
    }


    //==================================================================================
    //                                   SETTERS
    //==================================================================================


    public void setTechNum(int techNum) {
        this.techNum = techNum;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
