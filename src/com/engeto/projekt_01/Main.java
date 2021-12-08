package com.engeto.projekt_01;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        //načtení dat ze souboru
        Countries countries = new Countries();
        countries.readListFromFile(Settings.FILE_NAME);

        System.out.println("Automatický výběr zemí s DPH nad stanovený limit " + Settings.VAT_CHECK + "% :");
        List<Country> outputList;
        outputList = countries.countryAboveList(Settings.VAT_CHECK);
        countries.insertSpaces(outputList);

        outputList.sort(new FullVATComparator().reversed());

        System.out.println(countries.getOutPutLine(outputList, true));
        System.out.println("====================");
        System.out.println("Sazba VAT " + Settings.VAT_CHECK + " % nebo nižší nebo používají speciální sazbu: AT, CY, CZ,...");
        System.out.println(countries.getOutPutLine(outputList, false));
        countries.writeListToFile(Settings.FILE_NAME_OUTPUT_STD, Settings.VAT_CHECK);
        System.out.println();
        System.out.println("Výběr zemí s DPH nad uživatelsky stanoveným limitem.");
        System.out.println("Zadej číselnou hodnotu DPH: ");
        double enteredVAT = countries.userEnteredVAT();
        System.out.println("Výběr zemí s DPH nad stanovený limit " + enteredVAT + "% :");
        outputList = countries.countryAboveList(enteredVAT);
        countries.insertSpaces(outputList);
        outputList.sort(new FullVATComparator().reversed());
        System.out.println(countries.getOutPutLine(outputList, true));
        System.out.println("====================");
        System.out.println("Sazba VAT " + enteredVAT + " % nebo nižší nebo používají speciální sazbu: AT, CY, CZ,...");
        System.out.println(countries.getOutPutLine(outputList, false));
        countries.writeListToFile(Settings.FILE_NAME_OUTPUT_VAR + (int) enteredVAT + ".txt", enteredVAT);
        System.out.println();


    }

}
