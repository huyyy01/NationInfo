package com.nationinfo;

public class Nation {
    private static final String link = "http://img.geonames.org/flags/m/";
    public String countryName, countryCode;

    public Nation(String name, String countryCode){
        this.countryName = name;
        this.countryCode = link + countryCode.toLowerCase() + ".png";
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "{" +
                "countryName" + ": " + '\"' + countryName + '\"' + ", " +
                "countryCode" + ": " + '\"' + countryCode + '\"' +
                '}';
    }
}
