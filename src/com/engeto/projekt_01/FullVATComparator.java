package com.engeto.projekt_01;

import java.util.Comparator;

public class FullVATComparator implements Comparator<Country> {

    @Override
    public int compare(Country first, Country second) {
        return first.getFullRate().compareTo(second.getFullRate());
    }


}
