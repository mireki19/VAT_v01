package com.engeto.lekce05;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Countries countries = new Countries();
        countries.readListFromFile(Settings.FILE_NAME);

        Map<String, Double> countryRatesMap= new HashMap<>();
        countryRatesMap=countries.countryAboveMap(Settings.VAT_CHECK);
        TreeSet <String> countryAboveTS=new TreeSet<>();
        countryAboveTS=countries.countryAboveTS(Settings.VAT_CHECK);
        String[] countryAboveCodeString= countryAboveTS.toArray(new String[0]);
        System.out.println("Automatický výběr zemí s DPH nad stanovený limit 20%:");

        for (int i=0;i<= countryAboveTS.size()-1;i++){
            System.out.println(countryAboveCodeString[i]+" ("+countryRatesMap.get(countryAboveCodeString[i])+" %)");
        }
        System.out.println();
        System.out.println("Výběr zemí s DPH nad uživatelsky stanoveným limitem.");
        System.out.println("Zadej číselnou hodnotu DPH: ");
        double enteredVAT=countries.userEnteredVAT();
        countryRatesMap=countries.countryAboveMap(enteredVAT);
        countryAboveTS=countries.countryAboveTS(enteredVAT);
        countryAboveCodeString= countryAboveTS.toArray(new String[0]);
        System.out.println("Výběr zemí s DPH nad stanovený limit "+enteredVAT+"%: ");
        for (int i=0;i<= countryAboveTS.size()-1;i++){
            System.out.println(countryAboveCodeString[i]+" ("+countryRatesMap.get(countryAboveCodeString[i])+" %)");
        }
        // výběr států s nejvyšší DPH
        System.out.println();
        System.out.println("Stát(y) s nejvyšší DHP: ");
        List <Country> topVATCountries = new ArrayList<>();
        topVATCountries=countries.topVAT();
        Collections.sort(topVATCountries);
        for (int i=0;i<=topVATCountries.size()-1;i++){
            System.out.println(topVATCountries.get(i).getCountryName()+" sazba: "+topVATCountries.get(i).getFullRate());
        }

    }
}
