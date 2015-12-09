package tools;

/**
 * Created by shao on 2015/12/9.
 */
public class AddressComponent {

    private String country;
    private String province;
    private String city;
    private String district;
    private String street;
    private String street_number;
    private String country_code;
    private String direction;
    private String distance;

    public AddressComponent(String country, String province, String city, String street, String district, String street_number, String country_code, String direction, String distance) {
        this.country = country;
        this.province = province;
        this.city = city;
        this.street = street;
        this.district = district;
        this.street_number = street_number;
        this.country_code = country_code;
        this.direction = direction;
        this.distance = distance;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet_number() {
        return street_number;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return province+city+district+street+street_number;
    }
}
