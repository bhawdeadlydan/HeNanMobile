package sjtu.rfid.entity;

import java.util.List;

import rfid.service.inStagingInfo;
import rfid.service.stagingInfo;

/**
 * Created by shao on 2015/12/20.
 */
public class TmpSearchEntity {

    private List<inStagingInfo> in;
    private List<stagingInfo> out;

    public TmpSearchEntity(List<inStagingInfo> in, List<stagingInfo> out) {
        this.in = in;
        this.out = out;
    }

    public List<inStagingInfo> getIn() {
        return in;
    }

    public void setIn(List<inStagingInfo> in) {
        this.in = in;
    }

    public List<stagingInfo> getOut() {
        return out;
    }

    public void setOut(List<stagingInfo> out) {
        this.out = out;
    }
}
