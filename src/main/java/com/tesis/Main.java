package com.tesis;

import java.io.FileInputStream;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        try{

            String privilegePropPath = Thread.currentThread().getContextClassLoader().getResource("privilege.properties").getPath();

            Properties privilegeProps = new Properties();
            privilegeProps.load(new FileInputStream(privilegePropPath));

            System.out.println(privilegeProps.getProperty("GET.ping"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        new Application().init();
    }
}