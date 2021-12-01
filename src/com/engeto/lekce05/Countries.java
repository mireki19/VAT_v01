package com.engeto.lekce05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Double.parseDouble;

public class Countries {

    private List<Country> countries = new ArrayList();

    public void addCountry(Country country){
        countries.add(country);
    }



    public void readListFromFile(String fileName){
        countries.clear();

        try (Scanner scanner = new Scanner(new File(fileName))){
            int lineNumber=0;

            while (scanner.hasNextLine()) {
                lineNumber++;
                String nextLine = scanner.nextLine();
                String[] items = nextLine.split(Settings.ELEMENT_SEPARATOR);
                try {
                    double fullTax;
                    double lowTax;
                    fullTax=parseDouble(items[2]);
                    lowTax=parseDouble(items[3]);
                    boolean parkingRate;
                    parkingRate=Boolean.parseBoolean(items[4]);
                    Country country = new Country(items[0], items[1], fullTax, lowTax,parkingRate);
                    countries.add(country);
                } catch (Exception ex) {
                    System.err.println("Chybný formát dat na řádku "+lineNumber+" v souboru "+fileName+"\n"+
                            "načteny jen bezchybné řádky souboru");
                }

            }
        }
        catch (FileNotFoundException ex) {

        }


    }

    public Map countryAboveMap(double vATInput){
        Map<String, Double> countryAbove=new HashMap();
        for (Country country:countries){
            if (country.getFullRate()>vATInput)
                countryAbove.put(country.getCountryName(), country.getFullRate());

        }
        return countryAbove;

    }
    public TreeSet countryAboveTS(double vATInput){
        TreeSet<String> countryAboveTS = new TreeSet<>();
        for (Country country:countries){
            if (country.getFullRate()>vATInput)
                countryAboveTS.add(country.getCountryName());

        }
        return countryAboveTS;
    }

    public double userEnteredVAT(){
        Scanner sc = new Scanner(System.in);
        String input=sc.nextLine();
        double outputVAT=0;
        try {
            outputVAT=Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Neplatné zadání!");
            System.out.println("Bude nastaven standardní limit DPH: "+Settings.VAT_CHECK+"%");
            outputVAT=Settings.VAT_CHECK;
            e.printStackTrace();
        }
    return outputVAT;

    }

    public List topVAT(){
        List<Country> topVAT=new ArrayList<>();
        double temptop=0;
        for (Country country:countries) {
            if (country.getFullRate() >= temptop) {
                temptop = country.getFullRate();

            }
        }
            for (Country country:countries){
                if (country.getFullRate()>=temptop) {
                    topVAT.add(country);

                }

        }
        return topVAT;
    }




}
