package com.example.cis183_final_jacobwalker;

public class SessionData {


    private static Tech loggedInTech;

    public static Tech getLoggedInTech()
    {
        return loggedInTech;
    }

    public static void setLoggedInTech(Tech t)
    {
        loggedInTech = t;
    }
}
