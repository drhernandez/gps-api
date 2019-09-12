package com.tesis;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;

public class Till {
    public static String send(String endpoint, String username, String api_key, String json) throws Exception {
        final URL url = new URL(endpoint + "?username=" + username + "&api_key=" + api_key);
        final URLConnection conn = url.openConnection();

        HttpURLConnection http = null;
        try {
            http = (HttpURLConnection)conn;
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.setRequestProperty("Content-Length", "" + json.getBytes().length);
            http.setRequestMethod("POST");
            http.setRequestProperty("User-Agent", "Mozilla/5.0");
            http.setDoOutput(true);
            http.setDoInput(true);
            http.setUseCaches(false);

            OutputStream os = null;
            try {
                os = http.getOutputStream();
                os.write(json.getBytes("UTF-8"));
                os.flush();
            } finally {
                if(os != null) {
                    os.close();
                }
            }

            InputStream in = null;
            try {
                in = new BufferedInputStream(conn.getInputStream());
                byte[] contents = new byte[1024];
                int bytesRead = 0;
                StringBuffer outStr = new StringBuffer();
                while ((bytesRead = in.read(contents)) != -1) {
                    outStr.append(new String(contents, 0, bytesRead));
                }
                return outStr.toString();
            } catch (Exception e){
                System.out.println("Code: " + http.getResponseCode() + " Message: " + http.getResponseMessage());
                http.disconnect();
                throw e;
            } finally {
                if(in != null) {
                    in.close();
                }
            }
        } finally {
            if(http != null) {
                http.disconnect();
            }
        }
    }

//    public static void main(String[] args) {
//        try {
//            Till.send(
//                    "https://platform.tillmobile.com/api/send",
//                    "ff93cf9df324474d88c11e70472f4c",
//                    "5819e8e98176a0549235ed602c003954f3f01c3c",
//                    "{\"phone\":[\"+549351 5495416\"], \"text\":\"Hello Till from Java!\"}"
//            );
//        } catch(Exception e) {
//            System.out.println(e.toString());
//        }
//    }
}