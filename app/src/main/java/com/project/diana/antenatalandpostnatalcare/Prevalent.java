package com.project.diana.antenatalandpostnatalcare;

import java.util.Random;

public class Prevalent {
    public static User currentOnlineUser;

    public static final String UserPhoneKey = "UserPhone";
    public static final String UserPasswordKey = "UserPassword";

    public static String RandomInt2String(int high,int low){
        Random r = new Random();

        int result = r.nextInt(high-low) + low;
        return String.valueOf(result);
    }
}

