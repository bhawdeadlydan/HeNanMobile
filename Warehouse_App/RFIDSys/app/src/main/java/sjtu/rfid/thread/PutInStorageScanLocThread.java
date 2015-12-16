package sjtu.rfid.thread;

import android.os.Handler;
import android.os.Message;

/**
 * Created by user on 12/15/2015.
 */
public class PutInStorageScanLocThread extends Thread {
    private Handler handler;
    public PutInStorageScanLocThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run(){
        Message msg = handler.obtainMessage();
        msg.what = 0;
        msg.obj = scan();
        handler.sendMessage(msg);
    }

    public int scan(){
        int id = 1;
        return id;
    }
}
