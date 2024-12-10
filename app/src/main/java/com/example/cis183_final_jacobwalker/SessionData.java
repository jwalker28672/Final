package com.example.cis183_final_jacobwalker;

public class SessionData {


    private static Tech loggedInTech;
    private static RequestOrder curSelectedOrder;

    public static Tech getLoggedInTech()
    {
        return loggedInTech;
    }

    public static RequestOrder getCurSelectedOrder() {return curSelectedOrder; }

    public static void setLoggedInTech(Tech t)
    {
        loggedInTech = t;
    }

    public static void  setCurSelectedOrder(RequestOrder r)
    {
        curSelectedOrder = r;
    }
}
