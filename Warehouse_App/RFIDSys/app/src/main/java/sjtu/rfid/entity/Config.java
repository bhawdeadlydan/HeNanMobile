package sjtu.rfid.entity;

import java.util.ArrayList;

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
}
