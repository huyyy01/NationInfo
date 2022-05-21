package com.nationinfo;

public class NationInfo extends Nation {
    private static final String mapLink = "http://img.geonames.org/img/country/250/";
    String population, areaInSqKm, mapShape;
    //dùng hàm super để lấy lại giá trị biến của lớp cha (Nation)
    public NationInfo(String name, String countryCode) {
        super(name, countryCode);
    }

    public NationInfo(String name, String countryCode, String population, String area) {
        super(name, countryCode);
        this.population = population + " people";
        this.areaInSqKm = area + " km\u00B2";
        this.mapShape = mapLink + countryCode + ".png";
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getAreaInSqKm() {
        return areaInSqKm;
    }

    public void setAreaInSqKm(String areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }

    public String getMapShape() {
        return mapShape;
    }

    public void setMapShape(String mapShape) {
        this.mapShape = mapShape;
    }

    @Override
    // hiển thị các thông tin của quốc gia
    public String toString() {
        return "{" +
                "countryName" + ": " + '\"' + countryName + '\"' +
                ", countryCode" + ": " + '\"' + countryCode + '\"' +
                ", population" + ": " + '\"' + population + '\"' +
                ", areaInSqKm" + ": " + '\"' + areaInSqKm + '\"' +
                ", mapShape" + ": " + '\"' + mapShape + '\"' +
                '}';
    }
}
