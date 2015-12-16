package tools;

import org.json.JSONArray;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by shao on 2015/12/9.
 */
public class GeoCoder {
    private String path="http://api.map.baidu.com/geocoder/v2/?ak=F70d784bb9b4ab8020d77939dec77884&callback=renderReverse&output=json";
    private double lng=0.0;
    private double lat=0.0;
    public GeoCoder(double lng,double lat){
        this.lat=lat;
        this.lng=lng;
        this.path+="&location="+lat+","+lng;
    }
    public String getURL(){
        return this.path;
    }

    public  String doGet() throws Exception {
        URL localURL = new URL(path);
        URLConnection connection = localURL.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection)connection;

        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;

        if (httpURLConnection.getResponseCode() >= 300) {
            throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
        }

        try {
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);

            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return resultBuffer.toString();
    }

    public AddressComponent getAddress(String json){

        AddressComponent addressComponent=null;
        try{
            if(json.equals(null)||json.length()==0)
                return null;
            if(json.toString().contains("renderReverse")) {
                String s=json.substring(json.indexOf("(") + 1, json.length() - 1);
                JSONObject jsonObj = new JSONObject(s);
                Integer status = jsonObj.getInt("status");
                if (status.equals(0)) {
                    JSONObject addressObj = jsonObj.getJSONObject("result").getJSONObject("addressComponent");
                    addressComponent = new AddressComponent(addressObj.getString("country"), addressObj.getString("province"), addressObj.getString("city"), addressObj.getString("district"), addressObj.getString("street"),
                            addressObj.getString("street_number"),  String.valueOf(addressObj.getInt("country_code")), addressObj.getString("direction"), addressObj.getString("distance"));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return addressComponent;

    }
}
