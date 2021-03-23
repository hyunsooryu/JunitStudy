package com.gsshop.java8;

public class ServiceLogic {
    public static boolean checkPhoneNumber(String str){
        int size = str.length();
        int cnt = 0;
        for(int i = 0; i < size; i++){
            if(str.charAt(i) == '-')cnt++;
        }
        return cnt == 2;
    }
}
