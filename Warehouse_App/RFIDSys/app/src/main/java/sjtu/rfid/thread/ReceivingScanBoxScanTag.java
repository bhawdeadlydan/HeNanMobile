package sjtu.rfid.thread;

import java.util.Map;
import java.util.Set;

/**
 * Created by user on 12/15/2015.
 */
public class ReceivingScanBoxScanTag extends Thread {

    private Map<String,Set<String>> mReceivingBoxesItemsList;

    public ReceivingScanBoxScanTag(Map<String,Set<String>> mReceivingBoxesItemsList)
    {
        this.mReceivingBoxesItemsList = mReceivingBoxesItemsList;
    }
    @Override
    public void run() {
        super.run();
    }
}
