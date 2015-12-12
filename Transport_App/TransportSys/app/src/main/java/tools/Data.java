package tools;

import android.app.Application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by user on 12/12/2015.
 */
public class Data extends Application {

    private int checked = 0;
    private String name;
    private String phone;
    private String company;
    private String role;
    private String ipAddress;
    private int port;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public void saveData() {

        try {
            File filepath = this.getFilesDir();
            File file = new File(filepath.toString(),"info.txt");
            BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,false),"UTF-8"));
            fw.write(name+'\n');
            fw.write(phone+'\n');
            fw.write(company+'\n');
            fw.write(role+'\n');
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
