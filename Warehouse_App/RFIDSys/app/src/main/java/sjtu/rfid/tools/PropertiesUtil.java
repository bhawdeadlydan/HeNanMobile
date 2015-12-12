package sjtu.rfid.tools;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Created by shao on 2015/12/12.
 */
public class PropertiesUtil {
    public static Properties loadConfig(Context context) {
        Properties properties = new Properties();
        InputStream s = null;
        try {
            s = context.openFileInput("config.properties");
            properties.load(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static void saveConfig(Context context, Properties properties) {
        try {
            FileOutputStream s = context.openFileOutput("config.properties", Context.MODE_PRIVATE);
            properties.store(s, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
