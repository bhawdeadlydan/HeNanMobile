package sjtu.rfid.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 12/15/2015.
 */
public class Config {
    public static ArrayList<String> Location;
    static{
        Location = new ArrayList<>();
        Location.add("A11");
        Location.add("A12");
        Location.add("A21");
        Location.add("A22");
        Location.add("B11");
        Location.add("B12");
        Location.add("B21");
        Location.add("B22");
    }

    public static Map<String,String> LocationMap;
    static
    {
        LocationMap = new HashMap<>();
        LocationMap.put("A11","1");
        LocationMap.put("A12","2");
        LocationMap.put("A21","3");
        LocationMap.put("A22","4");
        LocationMap.put("B11","5");
        LocationMap.put("B12","6");
        LocationMap.put("B21","7");
        LocationMap.put("B22","8");
    }
}
