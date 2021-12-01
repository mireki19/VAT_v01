package com.engeto.lekce05;

public class Country implements Comparable<Country> {

    private String countryCode;
    private String countryName;
    private double fullRate;
    private double lowRate;
    private boolean parkingRate;


    public Country (String countryCode, String countryName, double fullRate, double lowRate, boolean parkingRate ){
        this.countryCode=countryCode;
        this.countryName=countryName;
        this.fullRate=fullRate;
        this.lowRate=lowRate;
        this.parkingRate=parkingRate;

    }



    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public double getFullRate() {
        return fullRate;
    }

    public void setFullRate(double fullRate) {
        this.fullRate = fullRate;
    }

    public double getLowRate() {
        return lowRate;
    }

    public void setLowRate(double lowRate) {
        this.lowRate = lowRate;
    }

    public boolean isParkingRate() {
        return parkingRate;
    }

    public void setParkingRate(boolean parkingRate) {
        this.parkingRate = parkingRate;
    }

    @Override
    public int compareTo(Country country) {
        return getCountryName().compareTo(country.getCountryName());
    }


}
