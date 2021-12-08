package com.engeto.projekt_01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

import static java.lang.Double.parseDouble;

public class Countries {

    private final List<Country> countries = new ArrayList();


    public void readListFromFile(String fileName) {
        countries.clear();

        try (Scanner scanner = new Scanner(new File(fileName))) {
            int lineNumber = 0;

            while (scanner.hasNextLine()) {
                lineNumber++;
                String nextLine = scanner.nextLine();
                String[] items = nextLine.split(Settings.ELEMENT_SEPARATOR);
                try {
                    double fullTax;
                    double lowTax;
                    items[2] = items[2].replace(',', '.');
                    items[3] = items[3].replace(',', '.');
                    fullTax = parseDouble(items[2]);
                    lowTax = parseDouble(items[3]);
                    boolean parkingRate;
                    parkingRate = Boolean.parseBoolean(items[4]);
                    Country country = new Country(items[0], items[1], fullTax
                            , lowTax, parkingRate, false);
                    countries.add(country);
                } catch (Exception ex) {
                    System.err.println("Chybný formát dat na řádku " + lineNumber + " v souboru " + fileName + "\n" +
                            "načteny jen bezchybné řádky souboru");
                }

            }
        } catch (FileNotFoundException ex) {
            System.out.println("Soubor nenalezen!");

        }


    }

    public void writeListToFile(String fileName, double VAT) {

        try
                (PrintWriter writer = new PrintWriter(fileName)) {

            String outPutLine = getOutPutLine(countries, true);
            writer.println(outPutLine);
            outPutLine = "====================";
            writer.println(outPutLine);
            outPutLine = "Sazba VAT " + VAT + " % nebo nižší nebo používají speciální sazbu: AT, CY, CZ,...";
            writer.println(outPutLine);
            outPutLine = getOutPutLine(countries, false);
            writer.println(outPutLine);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getOutPutLine(List<Country> countryList, Boolean above) {
        StringBuilder output = new StringBuilder();
        if (above) {
            for (int i = 0; i <= countryList.size() - 1; i++) {
                if (countryList.get(i).isVatAbove())
                    output.append(countryList.get(i).getCountryName()).append(" (").append(countryList.get(i).getCountryCode()).append("): ").append(countryList.get(i).getFullRate()).append(" %\n");
            }
        }

        else {
            for (int i = 0; i <= countryList.size() - 1; i++) {
                if (((!countryList.get(i).isVatAbove())) || (countryList.get(i).isParkingRate()))
                    output.append(countryList.get(i).getCountryName()).append(" (").append(countryList.get(i).getCountryCode()).append("): ").append(countryList.get(i).getFullRate()).append(" %\n");
            }
        }
        return output.toString();
    }


    public List countryAboveList(double vATInput) {
        // List<Country> countryAbove = new ArrayList<>();
        for (Country country : countries) {
            country.setVatAbove(country.getFullRate() > vATInput);

        }
        return countries;
    }


    public double userEnteredVAT() {
        double outputVAT;
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if (input.equals("")) outputVAT = Settings.VAT_CHECK;
        else {
            try {
                outputVAT = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Neplatné zadání!");
                System.out.println("Bude nastaven standardní limit DPH: " + Settings.VAT_CHECK + "%");
                outputVAT = Settings.VAT_CHECK;
                e.printStackTrace();
            }
        }
        return outputVAT;

    }

    public List<Country> topVAT() {
        List<Country> topVAT = new ArrayList<>();
        double temptop = 0;
        for (Country country : countries) {
            if (country.getFullRate() >= temptop) {
                temptop = country.getFullRate();

            }
        }
        for (Country country : countries) {
            if (country.getFullRate() >= temptop) {
                topVAT.add(country);

            }

        }
        return topVAT;
    }

    public void insertSpaces(List<Country> countryList) {
        int temp = 0;
        for (Country country : countryList) {
            if (country.getCountryName().length() > temp) temp = country.getCountryName().length();

        }
        for (Country country : countryList) {
            int cl = country.getCountryName().length();
            for (int i = cl; i < temp; i++) {
                country.setCountryName(country.getCountryName() + " ");

            }

        }
    }


}
