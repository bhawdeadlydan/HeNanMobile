package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import tools.GeoCoder;

/**
 * Created by shao on 2015/12/10.
 */
public class GeoCoderThread extends Thread{


    private double lng;
    private double lat;
    private String result="";
    private Handler handler;

    public GeoCoderThread(double lng, double lat, Handler handler){
        this.lat=lat;
        this.lng=lng;
        this.handler=handler;
    }

    @Override
    public void run() {
        Message msg = handler.obtainMessage();
        String result="";
        GeoCoder geoCoder=new GeoCoder(lng,lat);
        try{
            result=geoCoder.doGet();
        }catch (Exception e){
            e.printStackTrace();
        }
        msg.obj=geoCoder.getAddress(result).toString();
        handler.sendMessage(msg);
    }
}
