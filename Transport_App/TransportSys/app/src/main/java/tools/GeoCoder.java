package tools;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by shao on 2015/12/9.
 */
public class GeoCoder {
    private String url="http://api.map.baidu.com/geocoder/v2/?ak=E4805d16520de693a3fe707cdc962045&callback=renderReverse&output=json&pois=1";
    private double lng=0.0;
    private double lat=0.0;
    public GeoCoder(double lng,double lat){
        this.lat=lat;
        this.lng=lng;
        this.url+="&location="+lat+","+lng;
    }
    public AddressComponent getAddress(String url){

        AddressComponent addressComponent=null;
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            JSONObject jsonObj = new JSONObject(json.substring(json.indexOf("("),json.length()-1));
            String status=jsonObj.getJSONObject("status").toString();
            if(status.equals("0")){
                JSONObject addressObj=jsonObj.getJSONObject("result").getJSONObject("addressComponent");
                addressComponent=new AddressComponent(addressObj.getString("country"),addressObj.getString("province"),addressObj.getString("city"), addressObj.getString("street"),
                        addressObj.getString("street_number"), addressObj.getString("country_code"), addressObj.getString("direction"), addressObj.getString("distance"), addressObj.getString(""));
            }

        }catch(Exception e){
            e.printStackTrace();;
        }
        return addressComponent;
    }

    public static void main(String[] args){
        GeoCoder geoCoder=new GeoCoder(116.32298703399,39.983424051248);
        System.out.println(geoCoder.getAddress(geoCoder.url).toString());
    }
}
