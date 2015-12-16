package sjtu.rfid.entity;

import java.util.List;

import rfid.service.Good;
import rfid.service.LocationInfo;

/**
 * Created by shao on 2015/12/11.
 */
public class CheckByMatEntity {

    private Good good;
    private List<LocationInfo> locationInfoList;

    public CheckByMatEntity(Good good, List<LocationInfo> locationInfoList) {
        this.good = good;
        this.locationInfoList = locationInfoList;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public List<LocationInfo> getLocationInfoList() {
        return locationInfoList;
    }

    public void setLocationInfoList(List<LocationInfo> locationInfoList) {
        this.locationInfoList = locationInfoList;
    }
}
