package com.cos.photogramstart.util;

public class Script {

    public static String back(String msg) {

        System.out.println("-----------------Script------------------");
        StringBuffer sb = new StringBuffer();
              sb.append("<script>");
        sb.append("alert('" + msg + "');");
        sb.append("history.back();");
        sb.append("</script>");
               System.out.println(sb);
        return sb.toString();

    }
}
