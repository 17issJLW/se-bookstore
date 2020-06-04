package test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "中文";
        s = URLEncoder.encode(s,"UTF-8");
        System.out.println(s);
        String bodyString = URLDecoder.decode(s,"UTF-8");
        System.out.println(bodyString);
    }
}
