package tools;

import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
//    public String getJson(String path){
//        String json="";
//        try {
//            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//            byte[] data = new byte[2048];
//            int len = 0;
//            URL url = new URL(path);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            InputStream inStream = conn.getInputStream();
//            while ((len = inStream.read(data)) != -1) {
//                outStream.write(data, 0, len);
//            }
//            inStream.close();
//            json= new String(outStream.toByteArray());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//        return json;
//
//    }
    public AddressComponent getAddress(String json){

        AddressComponent addressComponent=null;
        try{
            if(json.equals(null)||json.length()==0)
                return null;
            if(json.toString().contains("renderReverse")) {
                String s=json.substring(json.indexOf("(") + 1, json.length() - 1);
                System.out.println(s);
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

//    public static void main(String[] args){
//        GeoCoder geoCoder=new GeoCoder(120.558957,31.325152);
//        geoCoder.getAddress();
//    }
}
