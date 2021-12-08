package com.engeto.projekt_01;

public class Country implements Comparable<Country> {

    private final String countryCode;
    private String countryName;
    private final Double fullRate;
    private final Double lowRate;
    private final boolean parkingRate;
    private boolean vatAbove;


    public Country(String countryCode, String countryName, double fullRate, double lowRate, boolean parkingRate
            , boolean vatAbove) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.fullRate = fullRate;
        this.lowRate = lowRate;
        this.parkingRate = parkingRate;
        this.vatAbove = vatAbove;

    }


    public String getCountryCode() {
        return countryCode;
    }

    public boolean isVatAbove() {
        return vatAbove;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Double getFullRate() {
        return fullRate;
    }

    public boolean isParkingRate() {
        return parkingRate;
    }

     public void setVatAbove(boolean vatAbove) {
        this.vatAbove = vatAbove;
    }

    @Override
    public int compareTo(Country country) {
        return getCountryName().compareTo(country.getCountryName());
    }


}
