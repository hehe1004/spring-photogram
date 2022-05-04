package com.cos.photogramstart.util;

public class Script {

    public static String back(String msg) {

        System.out.println("여기까지");
        StringBuffer sb = new StringBuffer();
        System.out.println("여기까지1");
        sb.append("<script>");
        sb.append("alert('" + msg + "');");
        sb.append("history.back();");
        sb.append("</script>");
        System.out.println("여기까지2");
        System.out.println(sb);
        return sb.toString();

    }
}
