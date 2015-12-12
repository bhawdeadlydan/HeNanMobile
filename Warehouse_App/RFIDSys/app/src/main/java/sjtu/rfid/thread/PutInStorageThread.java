package sjtu.rfid.thread;

import android.os.Handler;

import java.util.List;

import rfid.service.Good;

/**
 * Created by shao on 2015/12/11.
 */
public class PutInStorageThread extends Thread {

    private String goodsPos;
    private Handler handler;

    private List<Good> goodList;

    public PutInStorageThread() {
    }

    @Override
    public void run() {

    }
}
